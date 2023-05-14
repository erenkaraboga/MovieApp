package com.example.movie_application_eren_karaboga.data.remote.service

import com.example.movie_application_eren_karaboga.data.models.MovieDetail
import com.example.movie_application_eren_karaboga.data.models.MovieResult
import com.example.movie_application_eren_karaboga.data.models.MovieVideoResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    @GET("movie/{type}")
    fun getMovieList(@Path("type") type: String): Call<MovieResult>


    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movie_id: Int): Call<MovieDetail>


    @GET("search/movie")
    fun searchMovie(@Query("query") query: String): Call<MovieResult>

    @GET("movie/{movieId}/videos")
    fun getMovieVideos(@Path("movieId") movie_id: Int): Call<MovieVideoResponseModel>


}

