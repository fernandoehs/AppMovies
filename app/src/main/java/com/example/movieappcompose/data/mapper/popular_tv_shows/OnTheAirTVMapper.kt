package com.example.movieappcompose.data.mapper.popular_tv_shows

import com.example.movieappcompose.data.remote.dto.on_the_air_tv_shows_dto.OnTheAirTVDto
import com.example.movieappcompose.data.remote.dto.on_the_air_tv_shows_dto.OnTheAirTVResultDto
import com.example.movieappcompose.data.remote.dto.top_rated_tv_shows_dto.TopRatedTVDto
import com.example.movieappcompose.data.remote.dto.top_rated_tv_shows_dto.TopRatedTVResultDto
import com.example.movieappcompose.domain.model.on_the_air_tv.OnTheAirTV
import com.example.movieappcompose.domain.model.on_the_air_tv.OnTheAirTVResult


fun OnTheAirTVDto.toOnTheAirTV(): OnTheAirTV {
    return OnTheAirTV(
        results = results.map { it.toOnTheAirTVResult() }
    )
}
fun OnTheAirTVResultDto.toOnTheAirTVResult(): OnTheAirTVResult {
    return OnTheAirTVResult(
//        poster_path = poster_path ?: "",
//        vote_average = vote_average
        original_title = original_name,
        popularity = popularity,
        poster_path = poster_path,
        vote_count = vote_count,
        vote_average = vote_average,
        id = id
    )
}