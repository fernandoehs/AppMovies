package com.example.movieappcompose.domain.model.popular_tv_shows


data class PopularTVShowsResult(
    val original_title: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
    val first_air_date: String,
    val id: Int
)
