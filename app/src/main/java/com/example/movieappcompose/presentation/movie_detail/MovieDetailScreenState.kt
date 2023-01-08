package com.example.movieappcompose.presentation.movie_detail

import com.example.movieappcompose.domain.model.movie_details.MovieDetails
import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShowsResult
import com.example.movieappcompose.domain.model.top_rated.TopRatedMoviesResult


data class MovieDetailScreenState(
    val isLoading: Boolean = false,
    val MovieDetail: MovieDetails ?= null,
    val isLoadingDetail: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMostPopularMovies: Boolean = false,
    val endReached: Boolean = false,
    val listOfTopRatedMoviesItem: List<TopRatedMoviesResult>? = emptyList(),
    val page: Int?= 1,
    val isLoadingFromPaging: Boolean = false,
    val listOfTVShows: List<PopularTVShowsResult> = emptyList(),

    )
