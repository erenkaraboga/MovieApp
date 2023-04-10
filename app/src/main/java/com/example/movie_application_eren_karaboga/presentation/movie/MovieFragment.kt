package com.example.movie_application_eren_karaboga.presentation.movie

import com.example.movie_application_eren_karaboga.presentation.movie.adapter.MovieAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.databinding.FragmentMovieBinding
import com.example.movie_application_eren_karaboga.presentation.details.DetailsFragment

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
        setAdapter()
        initRecyclerView()
        configureRecyclerView()
        bindViewModel()
        return binding.root
    }

    private fun bindViewModel() {
        viewModel.getObserverLiveData().observe(
            viewLifecycleOwner
        ) { movieList ->
            if (movieList != null) {
                movieAdapter.setList(movieList.results);
            }
        }
        viewModel.loadPopularData("1");
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
        movieAdapter = MovieAdapter(object : MovieAdapter.ClickListener {
            override fun click(movieId: Int) {
                navigateDetailPage(movieId)
            }
        })
    }

    private fun navigateDetailPage(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)
        val movieDetailFragment = DetailsFragment()
        movieDetailFragment.arguments = bundle
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main, movieDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}