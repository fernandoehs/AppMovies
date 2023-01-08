package com.example.movieappcompose.presentation.on_air_tv

import com.example.movieappcompose.domain.model.on_the_air_tv.OnTheAirTVResult


data class OnAirTVState(
    val listOfOnAirTVShows: List<OnTheAirTVResult> = emptyList(),
    val isLoading: Boolean = false,
    val page: Int = 1,
    val endReached: Boolean = false,

    val isLoadingMostPopularMoviesFromPaging: Boolean = false,
    val isLoadingMostPopularMovies: Boolean = false,
    val isMoviesTab: Boolean = false,
    val isTVShowsTab: Boolean = false,
    val route: String = ""
)
