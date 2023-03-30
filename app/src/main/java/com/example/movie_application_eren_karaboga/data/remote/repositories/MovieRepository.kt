package com.example.movie_application_eren_karaboga.data.remote.repositories

import androidx.lifecycle.MutableLiveData
import com.example.movie_application_eren_karaboga.data.remote.service.MovieInterface
import com.example.movie_application_eren_karaboga.domain.models.Movie

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieInterface: MovieInterface){
    fun getPopularMovies(page:String,liveData: MutableLiveData<Movie>){
        movieInterface.getAllMovies(page).enqueue(object :Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}