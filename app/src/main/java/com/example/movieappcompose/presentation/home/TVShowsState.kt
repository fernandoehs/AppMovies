package com.example.movieappcompose.presentation.home

import com.example.movieappcompose.domain.model.top_rated.TopRatedMoviesResult


data class TVShowsState(
    val listOfTVShows: List<TopRatedMoviesResult> = emptyList(),
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val page: Int = 1
)
