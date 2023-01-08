package com.example.movieappcompose.data.mapper.movie_detail_mapper

import com.example.movieappcompose.data.remote.dto.movie_details_dto.CreatedByDto
import com.example.movieappcompose.data.remote.dto.movie_details_dto.GenreDto
import com.example.movieappcompose.data.remote.dto.movie_details_dto.MovieDetailDto
import com.example.movieappcompose.domain.model.movie_details.CreatedBy
import com.example.movieappcompose.domain.model.movie_details.Genre
import com.example.movieappcompose.domain.model.movie_details.MovieDetails

fun MovieDetailDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        original_language = original_language,
        original_title = original_name,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
       // release_date = release_date,
       //revenue = revenue,
       // runtime = runtime,
        id=id,
        status = status,
        tagline = tagline,
        name = title,
        vote_average = vote_average,
        vote_count = vote_count,
        genres = genres.map { it.toGenre() },
        first_air_date = first_air_date,
        last_air_date = last_air_date,
        created_by = created_by.map { it.toCreatedBy() },
        number_of_seasons = number_of_seasons,
        number_of_episodes = number_of_episodes
    )
}
fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun CreatedByDto.toCreatedBy(): CreatedBy {
    return CreatedBy(
        name = name
    )
}