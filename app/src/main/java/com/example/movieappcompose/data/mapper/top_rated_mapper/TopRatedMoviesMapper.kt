package com.example.movieappcompose.data.mapper.top_rated_mapper

import com.example.movieappcompose.data.remote.dto.top_rated_movies_dto.TopRatedMoviesDto
import com.example.movieappcompose.data.remote.dto.top_rated_movies_dto.TopRatedMoviesResultDto
import com.example.movieappcompose.domain.model.top_rated.TopRatedMovies
import com.example.movieappcompose.domain.model.top_rated.TopRatedMoviesResult

fun TopRatedMoviesResultDto.toTopRatedMoviesResult(): TopRatedMoviesResult {
    return TopRatedMoviesResult(
//        poster_path = poster_path,
//        original_title = original_title
    profile_path = profile_path,
        name = name,

    )
}

fun TopRatedMoviesDto.toTopRatedMovies(): TopRatedMovies {
    return TopRatedMovies(
        cast = cast.map { it.toTopRatedMoviesResult() }
    )
}

fun TopRatedMovies.toListOfTopRatedMovies(): List<TopRatedMovies> {
    return listOf(
        TopRatedMovies(
            cast = cast
        )
    )
}


