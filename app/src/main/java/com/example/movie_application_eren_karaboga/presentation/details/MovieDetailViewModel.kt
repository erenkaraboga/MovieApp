package com.example.movie_application_eren_karaboga.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.models.MovieDetail
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private var movieList: MutableLiveData<MovieDetail> = MutableLiveData()

    fun getObserverLiveData(): MutableLiveData<MovieDetail> = movieList

    fun getMovieDetail(movieId: Int) = movieRepository.getMovieDetail(movieId, movieList)
}

