package com.example.movieappcompose.presentation.view_all

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.movieappcompose.R
import com.example.movieappcompose.domain.repository.MoviesRepository
import com.example.movieappcompose.presentation.home.components.IconOrText
import com.example.movieappcompose.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewAllPopularMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

   var state by mutableStateOf(ViewAllMoviesState())
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getMostPopularMovies(nextPage)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            _eventFlow.emit(UiEvent.Message(it ?: UiText.unknownError()))
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                listOfAllPopularMovies = (state.listOfAllPopularMovies+ items),
                page = newKey,
                endReached = items.isEmpty(),
                isMoviesTab = true,
                isTVShowsTab = false
            )
        }
    )
    init {
        loadNextItems()
        getMostPopularMovies()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun getMostPopularMovies() {
        viewModelScope.launch {
            repository.getMostPopularMovies(1)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                listOfMostPopularMovies = result.data ?: emptyList(),
                                isLoadingMostPopularMovies = false
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoadingMostPopularMovies = false)
                        }
                        is Resource.Error -> {
                            state = state.copy(isLoadingMostPopularMovies = false)
                            _eventFlow.emit(UiEvent.Message(result.message ?: UiText.unknownError()))
                        }
                    }
                }
        }
    }

    @Composable
    fun TabView(
        modifier: Modifier = Modifier,
        viewModel: ViewAllPopularMoviesViewModel = hiltViewModel(),
        IconOrText: List<IconOrText>,
        onTabSelected: (selectedIndex: Int) -> Unit,
        navController: NavController
    ) {
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }
        val inactiveColor = Color.Gray
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                    //viewModel.onEvent(HomeScreenEvent.OnSearchClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
            IconOrText.forEachIndexed { index, iconOrText ->
                when (index) {
                    0 -> {
                        Text(
                            text = iconOrText.title.asString(),
                            color = if (selectedTabIndex == 0) Color.White else inactiveColor,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .clickable {
                                    selectedTabIndex = 0
                                    onTabSelected(index)
                                    viewModel.onEvent(ViewAllMovieEvent.OnMoviesTabClick)
                                    navController.navigate(Screen.ViewAllPopularMoviesScreen.route)

                                }
                        )
                    }
                    1 -> {
                        Text(
                            text = iconOrText.title.asString(),
                            color = if (selectedTabIndex == index) Color.White else inactiveColor,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .clickable {
                                    selectedTabIndex = index
                                    onTabSelected(index)
                                    viewModel.onEvent(ViewAllMovieEvent.OnTVShowsClick)
                                    navController.navigate(Screen.TopRatedTVScreen.route)
                                }
                        )
                    }
                    2 -> {
                        Text(
                            text = iconOrText.title.asString(),
                            color = if (selectedTabIndex == index) Color.White else inactiveColor,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .clickable {
                                    selectedTabIndex = index
                                    onTabSelected(index)
                                    viewModel.onEvent(ViewAllMovieEvent.OnTrailersClick)
                                    navController.navigate(Screen.OnAirTVScreen.route)

                                }
                        )
                    }
                }

            }
        }
    }


    fun onEvent(event: ViewAllMovieEvent) {
        when (event) {
            is ViewAllMovieEvent.OnSearchClick -> {
                viewModelScope.launch {
                    //navigator.popBackStack()
                }
            }
            is ViewAllMovieEvent.OnMoviesTabClick -> {
                state = state.copy(isMoviesTab = true, isTVShowsTab = false)
                // navController.navigate(Screen.ViewAllPopularMoviesScreen.route)

            }
            is ViewAllMovieEvent.OnTVShowsClick -> {
                state = state.copy(
                    isMoviesTab = false,
                    isTVShowsTab = true
                )
                //getTVShows()
            }
            is ViewAllMovieEvent.OnTrailersClick -> {
                state = state.copy(
                    isTVShowsTab = false,
                    isMoviesTab = false
                )
            }
        }
    }


}