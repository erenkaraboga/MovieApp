package com.example.movie_application_eren_karaboga.presentation.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.databinding.FragmentMovieBinding
import com.example.movie_application_eren_karaboga.presentation.movie.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory)[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        initRecyclerView()

        viewModel.getObserverLiveData().observe(viewLifecycleOwner
        ) { movieList ->
            if (movieList != null) {
                movieAdapter.setList(movieList.results);
            }
        }
        viewModel.loadPopularData("1");
        configureRecyclerView()

        return binding.root
    }

    private fun configureRecyclerView() {
        binding.apply {
            carouselRecyclerview.adapter = movieAdapter
            carouselRecyclerview.setAlpha(true)
            carouselRecyclerview.set3DItem(true)
            carouselRecyclerview.setIntervalRatio(0.7f)
            carouselRecyclerview.setInfinite(true)
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        val recyclerView = binding.carouselRecyclerview
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = manager
    }
}