package com.example.movieappcompose.presentation.movie_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.model.top_rated.TopRatedMoviesResult
import com.example.movieappcompose.domain.repository.MoviesRepository
import com.example.movieappcompose.util.Resource
import com.example.movieappcompose.util.UiEvent
import com.example.movieappcompose.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailScreenViewModel @Inject constructor(
    private val repository: MoviesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("tv_id")

    var state by mutableStateOf(MovieDetailScreenState(page = movieId))
    var TVShowsState by mutableStateOf(com.example.movieappcompose.presentation.home.TVShowsState())
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        movieId?.let { movieId ->
            getMovieDetail(movieId)
            getTVShows(movieId)
        }
    }


    fun onEvent(event: MovieDetailEvent) {
        when(event) {
            is MovieDetailEvent.Refresh -> {
                movieId?.let { movieId ->
                    getMovieDetail(movieId)
                    getTVShows(movieId)
                }
            }
        }
    }


    private fun getTVShows(movieId: Int) {
        viewModelScope.launch {
            repository.getTVShows(movieId)
                .collect { cast ->
                    when(cast) {
                        is Resource.Success -> {
                            TVShowsState = TVShowsState.copy(
                                listOfTVShows = cast.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            TVShowsState = TVShowsState.copy(
                                isLoading = true
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(isLoadingMostPopularMovies = false)
                            _eventFlow.emit(UiEvent.Message(cast.message ?: UiText.unknownError()))
                        }
                    }
                }
        }
    }

    fun loadNextItems() {
        viewModelScope.launch {
          //  paginator.loadNextItems()
        }
    }
    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetailById(movieId)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            state = state.copy(
                                isLoadingDetail = false,
                                MovieDetail = result.data
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoadingDetail = true)
                        }
                        is Resource.Error -> {
                            state = state.copy(isLoadingDetail = false)
                            _eventFlow.emit(UiEvent.Message(
                                result.message ?: UiText.unknownError()
                            ))
                        }
                    }
                }
        }
    }

}
