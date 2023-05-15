package com.example.movie_application_eren_karaboga.data.models.error


import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)