package com.example.movieappcompose.data.remote.dto.top_rated_movies_dto


data class TopRatedMoviesResultDto(

    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String = "https://pixy.org/src/9/94083.png"
)
