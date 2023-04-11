package com.example.movie_application_eren_karaboga.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import com.example.movie_application_eren_karaboga.data.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    var movieList: MutableLiveData<Result> = MutableLiveData()

    fun getObserverLiveData(): MutableLiveData<Result> = movieList

    fun loadPopularData(page: String) = movieRepository.getPopularMovies(page, movieList)

}