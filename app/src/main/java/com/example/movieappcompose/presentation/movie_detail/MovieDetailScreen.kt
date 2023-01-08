package com.example.movieappcompose.presentation.movie_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieappcompose.R
import com.example.movieappcompose.presentation.home.components.PosterTv
import com.example.movieappcompose.presentation.home.components.TVShowsItem
import com.example.movieappcompose.presentation.on_boarding.*
import com.example.movieappcompose.util.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.collectLatest


@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailScreenViewModel = hiltViewModel(),
    viewModelImage: MovieDetailScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val movieDetailImage = viewModelImage.state.MovieDetail
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.Message -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.onEvent(MovieDetailEvent.Refresh)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }


                    val IMAGE_BASE_UR = "https://image.tmdb.org/t/p/w500/"
                    PosterTv(modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .height(340.dp)
                        .width(270.dp),
                        imageUrl = "$IMAGE_BASE_UR/${movieDetailImage?.poster_path}" )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xFF232731),
                            shape = CircleShape.copy(
                                topStart = CornerSize(50.dp),
                                topEnd = CornerSize(50.dp),
                                bottomStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        )
                ) {
                    MovieDetail()

                    Review(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(
                                x = (-40).dp,
                                y = (-30).dp
                            )
                    )
                    if(viewModel.state.isLoadingDetail) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center),

                            )
                    }
                }

            }
        }
    }

}

//@Composable
//fun YouTubePlayer(
//    modifier: Modifier = Modifier,
//    viewModel: MovieDetailScreenViewModel = hiltViewModel()
//) {
//    val state = viewModel.state
//    var key by remember {
//        mutableStateOf("")
//    }
//    state.listOfMovie.forEachIndexed { index, movieVideoResult ->
//        if(index == 0) {
//            key = movieVideoResult.key
//        }
//    }
//    AndroidView(
//        factory = { context ->
//            YouTubePlayerView(context).apply {
//                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//                    override fun onReady(youTubePlayer: YouTubePlayer) {
//                        val videoId = key
//                        youTubePlayer.loadVideo(videoId, 0F)
//                    }
//                })
//            }
//        },
//        modifier = modifier
//    )
//}


@Composable
fun MovieDetail(
    viewModel: MovieDetailScreenViewModel = hiltViewModel()
) {
    val movieDetail = viewModel.state.MovieDetail
    LazyColumn(contentPadding = PaddingValues(25.dp)) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = ("Last Season : " + movieDetail?.number_of_seasons) ?: "",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = movieDetail?.original_title ?: "",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                }
                ///////
//                Spacer(modifier = Modifier.width(10.dp))
//
//                Text(
//                    text = "date: ${viewModel.getMovieCast(movieDetail!!.id)}",
//                    fontSize = 18.sp,
//                    color = Color.Gray,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Release date: ${movieDetail?.first_air_date}",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Release date: ${movieDetail?.id}",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(10.dp))
//                val URLP = "https://api.themoviedb.org/3/tv"
//                val CREDITS = "credits?api_key=2d65cb07fbbfae8909ba587398ace9b1"
                //"$IMAGE_BASE_UR/${movieDetailImage?.poster_path}" )
//                val stilo = viewModel.getMovieCast(movieDetail!!.id)
//                Text(
//                    text = "$stilo",
//                    fontSize = 18.sp,
//                    color = Color.Gray,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1
//                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Seasons: ${movieDetail?.number_of_seasons}",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Episodes: ${movieDetail?.number_of_episodes}",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
//                Spacer(modifier = Modifier.width(5.dp))
//                Text(
//                    text = "Last: ${movieDetail?.last_air_date}",
//                    fontSize = 15.sp,
//                    color = Color.Gray,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1
//                )

//                    Spacer(modifier = Modifier.height(5.dp))
//                    EpisodesSection(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    )

                Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = stringResource(id = R.string.authors),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Row {
                        viewModel.state.MovieDetail?.created_by?.forEachIndexed { _, genre ->
                            Text(
                                text = genre.name + ", "
                            )
                        }
                        Text(text = ".")

                    }

//                Text(
//                    text = "(${movieDetail?.created_by})",
//                    fontSize = 10.sp,
//                    color = Color.Gray,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1
//                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = movieDetail?.tagline ?: "",
                    fontSize = 17.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
             //   Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Box(
//                        modifier = Modifier
//                            .background(
//                                color = Color.Transparent,
//                                shape = RoundedCornerShape(25)
//                            )
//                            .border(
//                                width = 1.dp,
//                                color = Color.White,
//                                shape = RoundedCornerShape(25)
//                            )
//                            .width(48.dp)
//                            .height(24.dp),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "16+"
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(10.dp))
//                    Box(
//                        modifier = Modifier
//                            .background(
//                                color = Color.Gray,
//                                shape = RoundedCornerShape(25)
//                            )
//                            .border(
//                                width = 1.dp,
//                                color = Color.Gray,
//                                shape = RoundedCornerShape(25)
//                            )
//                            .width(48.dp)
//                            .height(24.dp),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "HD"
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(10.dp))
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(
//                            imageVector = Icons.Default.StarRate,
//                            contentDescription = stringResource(id = R.string.star_rate),
//                            tint = Color.Yellow
//                        )
//                        Text(
//                            text = movieDetail?.vote_average.toString()
//                        )
//                    }
                }


            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            Description(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Overview(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun Review(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val review = state.MovieDetail?.vote_average
    Box(
        modifier = modifier
            .width(70.dp)
            .height(130.dp)
            .background(
                color = Color.Red,
                shape = CircleShape
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                modifier = modifier
                    .size(60.dp)
                    .offset(
                        x = 39.dp,
                        y = 35.dp
                    ),
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray
                )
            ) {
                Icon(
                    imageVector = Icons.Default.StarRate,
                    contentDescription = stringResource(id = R.string.star_rate),
                    tint = Color.Yellow
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "$review/10",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.reviews),
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EpisodesSection(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailScreenViewModel = hiltViewModel()
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Seasons: ${viewModel.state.MovieDetail?.number_of_seasons}",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Episodes: ${viewModel.state.MovieDetail?.number_of_episodes}",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
    }
}


@Composable
fun Description(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailScreenViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.description),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Row {
            viewModel.state.MovieDetail?.genres?.forEachIndexed { _, genre ->
                Text(
                    text = genre.name + ", "
                )
            }
            Text(text = ".")
        }
    }

}




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Overview(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.overview),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = viewModel.state.MovieDetail?.overview ?: ""
        )

        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier

        ) {
            LazyRow(modifier = Modifier.align(Alignment.Center).padding(top = 50.dp)) {
                viewModel.TVShowsState.listOfTVShows.let { cast ->
                    items(cast.size) { i ->
                        Spacer(modifier = Modifier.width(35.dp))
                        TVShowsItem(
                            TVShows = cast[i],
                            modifier = Modifier
                                .height(215.dp)
                                .width(145.dp),
                            onItemClick = {

                            }
                        )
                    }
                }
            }
            if (viewModel.TVShowsState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

        }

    }
}
