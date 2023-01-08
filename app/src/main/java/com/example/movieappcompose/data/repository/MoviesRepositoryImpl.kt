package com.example.movieappcompose.data.repository

import com.example.movieappcompose.R
import com.example.movieappcompose.data.mapper.movie_detail_mapper.toMovieDetails
import com.example.movieappcompose.data.mapper.popular_tv_shows.toOnTheAirTVResult
import com.example.movieappcompose.data.mapper.popular_tv_shows.toPopularTVShowsResult
import com.example.movieappcompose.data.mapper.top_rated_mapper.toTopRatedMoviesResult
import com.example.movieappcompose.data.mapper.top_rated_tv_mapper.toTopRatedTVShowsResult
import com.example.movieappcompose.data.remote.MovieApi
import com.example.movieappcompose.domain.model.movie_details.MovieDetails
import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShowsResult
import com.example.movieappcompose.domain.model.on_the_air_tv.OnTheAirTVResult
import com.example.movieappcompose.domain.model.top_rated.TopRatedMoviesResult
import com.example.movieappcompose.domain.model.top_rated_tv_shows.TopRatedTVShowsResult
import com.example.movieappcompose.domain.repository.MoviesRepository
import com.example.movieappcompose.util.Resource
import com.example.movieappcompose.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MoviesRepository {


    override suspend fun getTopRatedMovies(page: Int): Flow<Resource<List<TopRatedMoviesResult>>> {
        return flow {
            emit(Resource.Loading())
            val remote = try {
                api.getTopRatedMovies(page)
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = UiText.StringResource(
                            resId = R.string.please_check_your_connection
                        )
                    )
                )
                null
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = (UiText.StringResource(
                            resId = R.string.Oops_something_went_wrong
                        ))
                    )
                )
                null
            }
            remote?.let { topRatedMovies ->
                emit(Resource.Loading())
                emit(Resource.Success(
                    data = topRatedMovies.cast.map { it.toTopRatedMoviesResult() }
                ))
            }
        }
    }


    override suspend fun getMostPopularMovies(page: Int): Flow<Resource<List<PopularTVShowsResult>>> = flow {
        val remote = try {
            api.getPopularTvShows(page = page)
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.please_check_your_connection
                    )
                )
            )
            null
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = (UiText.StringResource(
                        resId = R.string.Oops_something_went_wrong
                    ))
                )
            )
            null
        }

        remote?.let { TVShows ->
            emit(Resource.Loading())
            emit(Resource.Success(
                data = TVShows.results.map { it.toPopularTVShowsResult() }
            ))
        }
    }

    override suspend fun getTopRatedTVShows(page: Int): Flow<Resource<List<TopRatedTVShowsResult>>> = flow {
        val remote = try {
            api.getTopRatedTV(page = page)
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.please_check_your_connection
                    )
                )
            )
            null
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = (UiText.StringResource(
                        resId = R.string.Oops_something_went_wrong
                    ))
                )
            )
            null
        }

        remote?.let { TVShows ->
            emit(Resource.Loading())
            emit(Resource.Success(
                data = TVShows.results.map { it.toTopRatedTVShowsResult() }
            ))
        }
    }

    override suspend fun getOnTheAirTV(page: Int): Flow<Resource<List<OnTheAirTVResult>>> = flow {
        val remote = try {
            api.getOnAirTV(page = page)
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.please_check_your_connection
                    )
                )
            )
            null
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = (UiText.StringResource(
                        resId = R.string.Oops_something_went_wrong
                    ))
                )
            )
            null
        }

        remote?.let { TVShows ->
            emit(Resource.Loading())
            emit(Resource.Success(
                data = TVShows.results.map { it.toOnTheAirTVResult() }
            ))
        }
    }

    override suspend fun getTVShows(page: Int): Flow<Resource<List<TopRatedMoviesResult>>> = flow {
        val remote = try {
            api.getTopRatedMovies(tv_id = page )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.please_check_your_connection
                    )
                )
            )
            null
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = (UiText.StringResource(
                        resId = R.string.Oops_something_went_wrong
                    ))
                )
            )
            null
        }
        remote?.let { TVShows ->
            emit(Resource.Loading())
            emit(Resource.Success(
                data = TVShows.cast.map { it.toTopRatedMoviesResult() }
            ))
        }
    }


    override suspend fun getMovieDetailById(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        val remote = try {
            api.getMovieDetailById(tv_id = movieId) // tv id
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.please_check_your_connection
                    )
                )
            )
            null
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = (UiText.StringResource(
                        resId = R.string.Oops_something_went_wrong
                    ))
                )
            )
            null
        }
        remote?.let { detail ->
            emit(Resource.Loading())
            emit(Resource.Success(
                data = detail.toMovieDetails()
            ))
        }
    }
}
