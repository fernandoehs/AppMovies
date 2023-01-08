package com.example.movieappcompose.data.remote

import com.example.movieappcompose.data.remote.dto.movie_details_dto.MovieDetailDto
import com.example.movieappcompose.data.remote.dto.on_the_air_tv_shows_dto.OnTheAirTVDto
import com.example.movieappcompose.data.remote.dto.top_rated_movies_dto.TopRatedMoviesDto
import com.example.movieappcompose.data.remote.dto.top_rated_tv_shows_dto.TopRatedTVDto
import com.example.movieappcompose.data.remote.popular_tv_shows_dto.PopularTVShowsDto
import com.example.movieappcompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("/3/tv/{tv_id}/credits")
    suspend fun getTopRatedMovies(
        @Path("tv_id") tv_id: Int?,
        @Query("api_key") apikey: String = API_KEY,
    ): TopRatedMoviesDto

    @GET("/3/tv/on_the_air")
    suspend fun getOnAirTV(
        @Query("api_key") apikey: String = API_KEY,
        @Query("page") page: Int?
    ): OnTheAirTVDto

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTV(
        @Query("api_key") apikey: String = API_KEY,
        @Query("page") page: Int?
    ): TopRatedTVDto

    @GET("/3/tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apikey: String = API_KEY,
        @Query("page") page: Int = 1
    ): PopularTVShowsDto

    @GET("/3/tv/{tv_id}")
    suspend fun getMovieDetailById(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apikey: String = API_KEY
    ): MovieDetailDto
}