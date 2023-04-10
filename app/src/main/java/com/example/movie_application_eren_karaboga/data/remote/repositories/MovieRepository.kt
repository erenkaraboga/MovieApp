package com.example.movie_application_eren_karaboga.data.remote.repositories

import androidx.lifecycle.MutableLiveData
import com.example.movie_application_eren_karaboga.data.models.MovieDetail
import com.example.movie_application_eren_karaboga.data.remote.service.MovieInterface
import com.example.movie_application_eren_karaboga.data.models.Result

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieInterface: MovieInterface) {
    fun getPopularMovies(page: String, liveData: MutableLiveData<Result>) {
        movieInterface.getAllMovies(page).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getMovieDetail(movieId: Int, liveData: MutableLiveData<MovieDetail>) {
        movieInterface.getMovieDetail(movieId).enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}