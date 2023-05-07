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
    private var movieListPopular: LiveData<Result<MovieResult>> = MutableLiveData()
    private var movieUpComingList: LiveData<Result<MovieResult>> = MutableLiveData()
    private var movieTopRatedList: LiveData<Result<MovieResult>> = MutableLiveData()


    fun getObserverLiveData(): LiveData<Result<MovieResult>> = movieListPopular
    fun getObserverLiveDataUpComing(): LiveData<Result<MovieResult>> = movieUpComingList
    fun getObserverLiveDataTopRated(): LiveData<Result<MovieResult>> = movieTopRatedList

    fun loadPopularData(page: String) = movieRepository.getPopularMovies(
        page,
        movieListPopular as MutableLiveData<Result<MovieResult>>
    )
    fun loadUpComingData(page: String) = movieRepository.getUpComingMovies(
        page,
        movieUpComingList as MutableLiveData<Result<MovieResult>>
    )
    fun loadTopRatedData(page: String) = movieRepository.getTopRatedMovies(
        page,
        movieTopRatedList as MutableLiveData<Result<MovieResult>>
    )
}