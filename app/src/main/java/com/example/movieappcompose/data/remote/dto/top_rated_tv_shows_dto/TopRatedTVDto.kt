package com.example.movieappcompose.data.remote.dto.top_rated_tv_shows_dto

data class TopRatedTVDto(
    val page: Int,
    val results: List<TopRatedTVResultDto>,
    val total_pages: Int,
    val total_results: Int
)