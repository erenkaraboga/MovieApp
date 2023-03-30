package com.example.movie_application_eren_karaboga.data.remote.service

import com.example.movie_application_eren_karaboga.domain.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET("3/movie/popular?api_key=689569fe986992861685b815ae2e5a13")
     fun getAllMovies(
        @Query("page") query:String,
    ): Call<Movie>
}