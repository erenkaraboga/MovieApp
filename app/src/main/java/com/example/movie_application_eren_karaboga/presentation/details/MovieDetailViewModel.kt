package com.example.movie_application_eren_karaboga.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.models.MovieDetail
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.movie_application_eren_karaboga.base.utils.Result

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private var movieList: LiveData<Result<MovieDetail>> = MutableLiveData()

    fun getObserverLiveData(): LiveData<Result<MovieDetail>> = movieList

    fun getMovieDetail(movieId: Int) = movieRepository.getMovieDetail(
        movieId,
        movieList as MutableLiveData<Result<MovieDetail>>
    )
}


