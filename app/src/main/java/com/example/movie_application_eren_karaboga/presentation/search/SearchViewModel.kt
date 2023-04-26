package com.example.movie_application_eren_karaboga.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.models.Result
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieSearch @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private var movieList: LiveData<Result> = MutableLiveData()

    fun getObserverLiveData(): LiveData<Result> = movieList

    fun searchmovie(query: String) = movieRepository.searchMovie(query,
        movieList as MutableLiveData<Result>
    )

}