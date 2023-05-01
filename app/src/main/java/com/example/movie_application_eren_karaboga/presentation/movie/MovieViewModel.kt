package com.example.movie_application_eren_karaboga.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import com.example.movie_application_eren_karaboga.data.models.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.movie_application_eren_karaboga.base.utils.Result
@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private var movieList: LiveData<Result<MovieResult>> = MutableLiveData()

    fun getObserverLiveData(): LiveData<Result<MovieResult>> = movieList

    fun loadPopularData(page: String) = movieRepository.getPopularMovies(
        page,
        movieList as MutableLiveData<Result<MovieResult>>
    )
}