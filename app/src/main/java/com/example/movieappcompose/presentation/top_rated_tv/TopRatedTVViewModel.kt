package com.example.movieappcompose.presentation.top_rated_tv

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.repository.MoviesRepository
import com.example.movieappcompose.util.DefaultPaginator
import com.example.movieappcompose.util.UiEvent
import com.example.movieappcompose.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedTVViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    var state by mutableStateOf(TopRatedTVState())

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getTopRatedTVShows(nextPage)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            _eventFlow.emit(UiEvent.Message(it ?: UiText.unknownError()))
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                listOfTopRatedTVShows = state.listOfTopRatedTVShows + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )
    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun onEvent(event: TopRatedScreenEvent) {
        when (event) {
            is TopRatedScreenEvent.OnSearchClick -> {
                viewModelScope.launch {
                    //navigator.popBackStack()
                }
            }
            is TopRatedScreenEvent.OnMoviesTabClick -> {
                state = state.copy(
                    isMoviesTab = false,
                    isTVShowsTab = false)
                // navController.navigate(Screen.ViewAllPopularMoviesScreen.route)

            }
            is TopRatedScreenEvent.OnTVShowsClick -> {
                state = state.copy(
                    isMoviesTab = true,
                    isTVShowsTab = false
                )
                //getTVShows()
            }
            is TopRatedScreenEvent.OnTrailersClick -> {
                state = state.copy(
                    isTVShowsTab = false,
                    isMoviesTab = false
                )
            }
        }
    }

}