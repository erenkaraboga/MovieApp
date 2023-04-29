package com.example.movie_application_eren_karaboga.presentation.movie


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
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
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
        viewModel.loadPopularData("1");
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        initRecyclerView()
        configureRecyclerView()

    }
    fun listenSearchTap(){
        binding.imageView2.setOnClickListener{
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
                is MovieRepository.Result.Success -> {
                    val movieList = result.data?.results
                    if (movieList != null) {
                        movieAdapter.setList(movieList)
                    }
                }
                is MovieRepository.Result.Error -> {
                   println(result.message)
                }
            }
        }
    }

    private fun configureRecyclerView() {
        binding.apply {
            carouselRecyclerview.adapter = movieAdapter
            carouselRecyclerview.setAlpha(true)
            carouselRecyclerview.set3DItem(true)
            carouselRecyclerview.setIntervalRatio(0.6f)
            carouselRecyclerview.setInfinite(true)
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        val recyclerView = binding.carouselRecyclerview
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = manager
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