package com.example.movie_application_eren_karaboga.presentation.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.databinding.FragmentMovieBinding
import com.example.movie_application_eren_karaboga.domain.models.Movie
import com.example.movie_application_eren_karaboga.presentation.movie.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        val verticalList = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        val recyclerView = binding.carouselRecyclerview
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = verticalList
        viewModel.getObserverLiveData().observe(viewLifecycleOwner, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if (t != null) {
                    movieAdapter.setList(t.results);
                }
            }

        })
        viewModel.loadPopularData("1");
        binding.apply {
            carouselRecyclerview.adapter = movieAdapter
            carouselRecyclerview.setAlpha(true)
            carouselRecyclerview.set3DItem(true)
            carouselRecyclerview.setIntervalRatio(0.7f)

            carouselRecyclerview.setInfinite(true)
        }
        return binding.root
    }


}