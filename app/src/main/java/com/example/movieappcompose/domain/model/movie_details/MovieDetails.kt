package com.example.movieappcompose.domain.model.movie_details


data class MovieDetails(
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val status: String,
    val tagline: String,
    val name: String?,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<Genre>,
    val first_air_date: String,
    val last_air_date:String,
    val created_by: List<CreatedBy>,
    val number_of_seasons:String,
    val number_of_episodes:String,
    val id:Int
)
