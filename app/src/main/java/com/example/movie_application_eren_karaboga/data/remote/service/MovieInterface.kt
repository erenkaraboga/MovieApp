package com.example.movie_application_eren_karaboga.data.remote.service

import com.example.movie_application_eren_karaboga.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET("3/movie/popular")
    suspend fun getAllMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<MoviesResponse>


}