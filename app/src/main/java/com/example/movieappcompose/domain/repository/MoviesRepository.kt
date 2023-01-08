package com.example.movieappcompose.domain.repository

import com.example.movieappcompose.domain.model.movie_details.MovieDetails
import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShowsResult
import com.example.movieappcompose.domain.model.top_rated.TopRatedMoviesResult
import com.example.movieappcompose.domain.model.on_the_air_tv.OnTheAirTVResult
import com.example.movieappcompose.domain.model.top_rated_tv_shows.TopRatedTVShowsResult
import com.example.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getTopRatedMovies(page: Int): Flow<Resource<List<TopRatedMoviesResult>>>
    suspend fun getMostPopularMovies(page: Int): Flow<Resource<List<PopularTVShowsResult>>>
    suspend fun getTopRatedTVShows(page: Int): Flow<Resource<List<TopRatedTVShowsResult>>>
    suspend fun getOnTheAirTV(page: Int): Flow<Resource<List<OnTheAirTVResult>>>
    suspend fun getTVShows(page: Int): Flow<Resource<List<TopRatedMoviesResult>>>
    suspend fun getMovieDetailById(movieId: Int): Flow<Resource<MovieDetails>>
}