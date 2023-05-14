package com.example.movie_application_eren_karaboga.data.models


import com.google.gson.annotations.SerializedName

data class MovieVideoResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<MovieVideoModel>
)
