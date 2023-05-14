package com.example.movie_application_eren_karaboga.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.models.MovieDetail
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.movie_application_eren_karaboga.base.utils.Result
import com.example.movie_application_eren_karaboga.data.models.MovieVideoResponseModel

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private var movieList: LiveData<Result<MovieDetail>> = MutableLiveData()

    private var movieVideoList: LiveData<Result<MovieVideoResponseModel>> = MutableLiveData()

    fun getObserverLiveData(): LiveData<Result<MovieDetail>> = movieList

    fun getObserverLiveDataVideo(): LiveData<Result<MovieVideoResponseModel>> = movieVideoList

    fun getMovieDetail(movieId: Int) = movieRepository.getMovieDetail(
        movieId,
        movieList as MutableLiveData<Result<MovieDetail>>
    )

    fun getMovieVideos(movieId: Int) = movieRepository.getMovieVideos(
        movieId,
        movieVideoList as MutableLiveData<Result<MovieVideoResponseModel>>
    )
}


