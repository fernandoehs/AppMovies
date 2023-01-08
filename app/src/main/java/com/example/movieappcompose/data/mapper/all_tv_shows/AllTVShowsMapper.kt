package com.example.movieappcompose.data.mapper.all_tv_shows

import com.example.movieappcompose.data.remote.popular_tv_shows_dto.PopularTVShowsDto
import com.example.movieappcompose.data.remote.popular_tv_shows_dto.PopularTVShowsResultDto
import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShows
import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShowsResult


fun PopularTVShowsDto.toPopularTVShows(): PopularTVShows {
    return PopularTVShows(
        results = results.map { it.toPopularTVShowsResult() }
    )
}
fun PopularTVShowsResultDto.toPopularTVShowsResult(): PopularTVShowsResult {
    return PopularTVShowsResult(

        original_title = original_name,
        popularity = popularity,
        poster_path = poster_path,
        vote_count = vote_count,
        vote_average = vote_average,
        first_air_date = first_air_date,
        id = id

    )
}