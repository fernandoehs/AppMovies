package com.example.movieappcompose.presentation.on_air_tv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieappcompose.R
import com.example.movieappcompose.presentation.home.components.IconOrText
import com.example.movieappcompose.presentation.home.components.IconOrTextList
import com.example.movieappcompose.presentation.home.components.OnAirTVItem

import com.example.movieappcompose.util.Screen

@ExperimentalMaterialApi
@Composable
fun OnAirTVScreen(
    viewModel: OnAirTVViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    var loadingSpacing by remember {
        mutableStateOf(0.dp)
    }

    var selectedTabIndex by remember {
        mutableStateOf(2)
    }

    Box(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {

        TabViewOnAir(
            IconOrText = IconOrTextList,
            onTabSelected = {
                selectedTabIndex = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            navController = navController
        )
        when (selectedTabIndex) {
            0 -> {

            }
            1 -> {

            }
            2 -> {

            }
        }
    }

    Box(modifier = Modifier.padding(top = 30.dp)) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {

            items(state.listOfOnAirTVShows.size) { i ->
                if(i >= state.listOfOnAirTVShows.size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.loadNextItems()
                }
                val movies = state.listOfOnAirTVShows[i]

                OnAirTVItem(
                    onAirTv = movies,
                    onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route + "?tv_id=${movies.id}")
                    },
                    modifier = Modifier
                        .height(215.dp)
                        .width(145.dp)
                        .padding(10.dp),
                )
            }
        }
        if(state.isLoading) {
            if(state.page == 1) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            } else {
                loadingSpacing = 50.dp
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        } else {
            loadingSpacing = 0.dp
        }
    }
}

@Composable
fun TabViewOnAir(
    modifier: Modifier = Modifier,
    viewModel: OnAirTVViewModel = hiltViewModel(),
    IconOrText: List<IconOrText>,
    onTabSelected: (selectedIndex: Int) -> Unit,
    navController: NavController
) {
    var selectedTabIndex by remember {
        mutableStateOf(2)
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
                        color = if (selectedTabIndex == index) Color.White else inactiveColor,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable {
                                selectedTabIndex = index
                                onTabSelected(index)
                                viewModel.onEvent(OnAirTVScreenEvent.OnMoviesTabClick)
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
                                viewModel.onEvent(OnAirTVScreenEvent.OnTVShowsClick)
                                navController.navigate(Screen.TopRatedTVScreen.route)
                            }
                    )
                }
                2 -> {
                    Text(
                        text = iconOrText.title.asString(),
                        color = if (selectedTabIndex == 2) Color.White else inactiveColor,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable {
                                selectedTabIndex = 2
                                onTabSelected(index)
                                viewModel.onEvent(OnAirTVScreenEvent.OnTrailersClick)
                                navController.navigate(Screen.OnAirTVScreen.route)

                            }
                    )
                }
            }

        }
    }
}