package com.example.movieappcompose.domain.model.top_rated_tv_shows


data class TopRatedTVShowsResult(
    val original_title: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
    val id: Int


)
