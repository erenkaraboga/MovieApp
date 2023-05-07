package com.example.movie_application_eren_karaboga.presentation.movie

import com.example.movie_application_eren_karaboga.base.utils.Result

import com.example.movie_application_eren_karaboga.presentation.search.SearchFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.databinding.FragmentMovieBinding

import com.example.movie_application_eren_karaboga.presentation.details.DetailsFragment
import com.example.movie_application_eren_karaboga.presentation.movie.adapter.MovieAdapter


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        listenSearchTap()
        bindViewModel()
        viewModel.loadPopularData("1")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
    }

    private fun listenSearchTap() {
        binding.IvSearch.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, SearchFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun bindViewModel() {
        viewModel.getObserverLiveData().observe(
            viewLifecycleOwner
        ) { result ->
            when (result) {
                is Result.Success -> {
                    val movieList = result.data!!.results
                    movieAdapter.setList(movieList)
                }
                is Result.Error -> {
                    println(result.message)
                }
            }
        }
    }


    private fun setAdapter() {
        movieAdapter = MovieAdapter(object : MovieAdapter.OnClickListener {
            override fun onclick(movieId: Int) {
                navigateDetailPage(movieId)
            }
        })
    }

    private fun navigateDetailPage(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.MOVIE_ID, movieId)
        val movieDetailFragment = DetailsFragment()
        movieDetailFragment.arguments = bundle
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main, movieDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}