package com.example.movieappcompose.data.remote.dto.on_the_air_tv_shows_dto

data class OnTheAirTVDto(
    val page: Int,
    val results: List<OnTheAirTVResultDto>,
    val total_pages: Int,
    val total_results: Int
)