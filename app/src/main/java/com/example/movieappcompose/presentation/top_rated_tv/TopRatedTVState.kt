package com.example.movieappcompose.presentation.top_rated_tv

import com.example.movieappcompose.domain.model.top_rated_tv_shows.TopRatedTVShowsResult


data class TopRatedTVState(
    val listOfTopRatedTVShows: List<TopRatedTVShowsResult> = emptyList(),
    val isLoading: Boolean = false,
    val page: Int = 1,
    val endReached: Boolean = false,

    val isLoadingMostPopularMoviesFromPaging: Boolean = false,
    val isLoadingMostPopularMovies: Boolean = false,
    val isMoviesTab: Boolean = false,
    val isTVShowsTab: Boolean = false,
    val route: String = ""
)
