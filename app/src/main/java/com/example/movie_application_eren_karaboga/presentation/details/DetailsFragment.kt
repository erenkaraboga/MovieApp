package com.example.movie_application_eren_karaboga.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.extensions.loadFullImage
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.databinding.FragmentDetailsBinding
import com.example.movie_application_eren_karaboga.presentation.details.adapter.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.example.movie_application_eren_karaboga.base.utils.Result

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
        binding.IvBack. setOnClickListener {
            parentFragmentManager.popBackStack()
        }
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
        viewModel.getObserverLiveData().observe(viewLifecycleOwner) { movieDetailResult ->
            when (movieDetailResult) {
                is Result.Success -> {
                    val movieDetail = movieDetailResult.data
                    genreAdapter.setList(movieDetail!!.genres)
                    binding.TvMovieName.text = movieDetail.title
                    binding.TvDuration.text = movieDetail.releaseDate
                    binding.TvRating.text = movieDetail.voteAverage.toString()
                    binding.TvGenre.text = movieDetail.originalLanguage
                    binding.TvDescMovie.text = movieDetail.overview
                    Glide.with(binding.IvPoster)
                            .load(movieDetail.posterPath.loadFullImage())
                            .placeholder(R.drawable.loading_image)
                            .error(R.drawable.error_image)
                            .transform(CenterInside(), RoundedCorners(30))
                            .into(binding.IvPoster)
                }
                is Result.Error -> {

                }

            }
        }
        viewModel.getMovieDetail(movieId)
    }
}