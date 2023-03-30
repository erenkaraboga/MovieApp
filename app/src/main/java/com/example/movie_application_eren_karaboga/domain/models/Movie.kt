package com.example.movie_application_eren_karaboga.domain.models

data class Movie(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)