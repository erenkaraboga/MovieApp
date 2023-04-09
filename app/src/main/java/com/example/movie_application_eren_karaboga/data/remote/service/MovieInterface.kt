package com.example.movie_application_eren_karaboga.data.remote.service

import com.example.movie_application_eren_karaboga.data.models.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET("3/movie/popular")
     fun getAllMovies(
        @Query("page") query:String,
    ): Call<Result>
}