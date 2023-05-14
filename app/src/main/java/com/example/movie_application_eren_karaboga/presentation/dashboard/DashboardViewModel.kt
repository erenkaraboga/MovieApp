package com.example.movie_application_eren_karaboga.presentation.dashboard


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application_eren_karaboga.data.remote.repositories.MovieRepository
import com.example.movie_application_eren_karaboga.data.models.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.movie_application_eren_karaboga.base.utils.Result

@HiltViewModel
class DashboardViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val movieListMap: MutableMap<String, LiveData<Result<MovieResult>>> = mutableMapOf()

    fun getObserverLiveData(type: String): LiveData<Result<MovieResult>> {
        if (!movieListMap.containsKey(type)) {
            movieListMap[type] = MutableLiveData()
            loadMovieData(type)
        }
        return movieListMap[type]!!
    }

    fun loadMovieData(type: String) {
        movieRepository.getMovieList(
            type,
            movieListMap[type] as MutableLiveData<Result<MovieResult>>
        )
    }

}