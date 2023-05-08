package com.example.movie_application_eren_karaboga.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.base.utils.Result
import com.example.movie_application_eren_karaboga.data.models.MovieResult
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private var movieList: LiveData<Result<MovieResult>> = MutableLiveData()

    fun getObserverLiveData(): LiveData<Result<MovieResult>> = movieList

    fun searchMovie(query: String) = movieRepository.searchMovie(query,
        movieList as MutableLiveData<Result<MovieResult>>
    )
}