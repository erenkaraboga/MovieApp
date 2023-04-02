package com.example.movie_application_eren_karaboga.config.di

import com.example.movie_application_eren_karaboga.BuildConfig
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.config.client.RetrofitInterceptoro
import com.example.movie_application_eren_karaboga.data.remote.service.MovieInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(RetrofitInterceptoro())
            .build()
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MovieInterface::class.java)
    }
}