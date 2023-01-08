package com.example.movieappcompose.presentation.view_all


import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShowsResult


data class ViewAllMoviesState(
    val listOfAllPopularMovies: List<PopularTVShowsResult> = emptyList(),
    val listOfMostPopularMovies: List<PopularTVShowsResult> = emptyList(),

    val isLoading: Boolean = false,
    val page: Int = 1,
    val endReached: Boolean = false,

    val isLoadingMostPopularMoviesFromPaging: Boolean = false,
    val isLoadingMostPopularMovies: Boolean = false,
    val isMoviesTab: Boolean = false,
    val isTVShowsTab: Boolean = false,
    val route: String = ""
)
