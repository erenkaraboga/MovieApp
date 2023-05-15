package com.example.movie_application_eren_karaboga.data.remote.repositories

import androidx.lifecycle.MutableLiveData
import com.example.movie_application_eren_karaboga.data.models.MovieDetail
import com.example.movie_application_eren_karaboga.data.models.MovieResult
import com.example.movie_application_eren_karaboga.data.remote.service.MovieInterface
import com.example.movie_application_eren_karaboga.base.utils.Result
import com.example.movie_application_eren_karaboga.data.models.MovieVideoResponseModel
import com.example.movie_application_eren_karaboga.data.models.error.ErrorBody
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieInterface: MovieInterface) {

    fun getMovieList(type: String, liveData: MutableLiveData<Result<MovieResult>>) {
        movieInterface.getMovieList(type).enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if(response.isSuccessful) {
                    liveData.postValue(Result.Success(response.body()))
                } else {
                    val errorBody = response.errorBody()
                    val gson = GsonBuilder().create()
                    val errorResponse = gson.fromJson(errorBody?.string(), ErrorBody::class.java)
                    val errorMessage = errorResponse?.statusMessage ?: "Unknown Error"
                    liveData.postValue(Result.Error(errorMessage))
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                liveData.postValue(Result.Error(t.message ?: "Unknown Error"))
            }
        })
    }

    fun getMovieDetail(movieId: Int, liveData: MutableLiveData<Result<MovieDetail>>) {
        movieInterface.getMovieDetail(movieId).enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if(response.isSuccessful) {
                    liveData.postValue(Result.Success(response.body()))
                } else {
                    val errorBody = response.errorBody()
                    val gson = GsonBuilder().create()
                    val errorResponse = gson.fromJson(errorBody?.string(), ErrorBody::class.java)
                    val errorMessage = errorResponse?.statusMessage ?: "Unknown Error"
                    liveData.postValue(Result.Error(errorMessage))
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                liveData.postValue(Result.Error(t.message ?: "Unknown Error"))
            }
        })
    }

    fun searchMovie(query: String, liveData: MutableLiveData<Result<MovieResult>>) {
        movieInterface.searchMovie(query).enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if(response.isSuccessful) {
                    liveData.postValue(Result.Success(response.body()))
                } else {
                    val errorBody = response.errorBody()
                    val gson = GsonBuilder().create()
                    val errorResponse = gson.fromJson(errorBody?.string(), ErrorBody::class.java)
                    val errorMessage = errorResponse?.statusMessage ?: "Unknown Error"
                    liveData.postValue(Result.Error(errorMessage))
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                liveData.postValue(Result.Error(t.message ?: "Unknown Error"))
            }
        })
    }

    fun getMovieVideos(movieId: Int, liveData: MutableLiveData<Result<MovieVideoResponseModel>>) {
        movieInterface.getMovieVideos(movieId)
            .enqueue(object : Callback<MovieVideoResponseModel> {
                override fun onResponse(
                    call: Call<MovieVideoResponseModel>,
                    response: Response<MovieVideoResponseModel>,
                ) {

                    if(response.isSuccessful) {
                        liveData.postValue(Result.Success(response.body()))
                    } else {
                        val errorBody = response.errorBody()
                        val gson = GsonBuilder().create()
                        val errorResponse = gson.fromJson(errorBody?.string(), ErrorBody::class.java)
                        val errorMessage = errorResponse?.statusMessage ?: "Unknown Error"
                        liveData.postValue(Result.Error(errorMessage))
                    }
                }

                override fun onFailure(call: Call<MovieVideoResponseModel>, t: Throwable) {
                    liveData.postValue(Result.Error(t.message ?: "Unknown Error"))
                }
            })
    }


}