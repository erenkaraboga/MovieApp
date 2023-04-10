package com.example.movie_application_eren_karaboga.config.di

import com.example.movie_application_eren_karaboga.BuildConfig

import com.example.movie_application_eren_karaboga.data.remote.service.MovieInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private var clientInterceptor = Interceptor { chain: Interceptor.Chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.api_key)
            .build()
        val request: Request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }
    @Singleton
    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MovieInterface::class.java)
    }

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(clientInterceptor).addInterceptor(loggingInterceptor)
            .build()
    }
}