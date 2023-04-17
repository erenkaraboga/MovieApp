package com.example.movie_application_eren_karaboga.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.base.extensions.loadPosterUrl
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.databinding.FragmentDetailsBinding
import com.example.movie_application_eren_karaboga.presentation.movie.adapter.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var movieId: Int = 0
    private val viewModel by viewModels<MovieDetailViewModel>()
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getInt(Constants.MOVIE_ID)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        bindViewModel(movieId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        setRecyclerView()

    }

    private fun setRecyclerView() {
        val manager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        val recyclerView = binding.genreRecyclerView
        recyclerView.adapter = genreAdapter
        recyclerView.layoutManager = manager
    }

    private fun setAdapter() {
        genreAdapter = GenreAdapter()
    }

    private fun bindViewModel(movieId: Int) {
        viewModel.getObserverLiveData().observe(
            viewLifecycleOwner
        ) { movieList ->
            if (movieList != null) {
                genreAdapter.setList(movieList.genres)
                binding.TvMovieName.text = movieList.title
                binding.TvDuration.text = movieList.releaseDate
                binding.TvRating.text = movieList.voteAverage.toString()
                binding.TvGenre.text = movieList.originalLanguage
                binding.TvDescMovie.text = movieList.overview
                binding.IvPoster.loadPosterUrl(movieList.posterPath)
            }
        }
        viewModel.getMovieDetail(movieId)
    }
}