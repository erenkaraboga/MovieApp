package com.example.movie_application_eren_karaboga.presentation.movie

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import com.example.movie_application_eren_karaboga.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository):ViewModel(){
    var movieList  : MutableLiveData<Movie>
    init {
        movieList =MutableLiveData()
    }
    fun getObserverLiveData():MutableLiveData<Movie>{
        return movieList
    }

    fun loadPopularData(page: String){
       movieRepository.getPopularMovies(page,movieList)
    }

}