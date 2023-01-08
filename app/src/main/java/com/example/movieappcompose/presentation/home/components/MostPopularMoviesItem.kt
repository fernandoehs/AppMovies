package com.example.movieappcompose.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieappcompose.R
import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShowsResult

@ExperimentalMaterialApi
@Composable
fun MostPopularMoviesItem(
    modifier: Modifier = Modifier,
    mostPopularMovies:PopularTVShowsResult,
    onItemClick: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10)),
        onClick = {
            onItemClick()
        },

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val image = "https://image.tmdb.org/t/p/original${mostPopularMovies.poster_path}"
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(image)
                        .crossfade(true)
                        .build()
                ),
                contentDescription = stringResource(id = R.string.top_rated_movies_poster),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            StarRatings(
                mostPopularMovies = mostPopularMovies,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .width(55.dp)
                    .height(35.dp)
            )

            ReleaseDate(
                mostPopularMovies = mostPopularMovies,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .width(100.dp)
                    .height(25.dp)
            )
            DescriptionTV(
                mostPopularMovies = mostPopularMovies,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(120.dp)
                    .height(25.dp)
            )
        }
    }
}

@Composable
fun StarRatings(
    modifier: Modifier = Modifier,
    mostPopularMovies: PopularTVShowsResult
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 20.dp
                )
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Default.StarRate,
                contentDescription = stringResource(id = R.string.star_rate),
                colorFilter = ColorFilter.tint(color = Color.Yellow),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = mostPopularMovies.vote_average.toString()

            )
        }
    }
}

@Composable
fun ReleaseDate(
    modifier: Modifier = Modifier,
    mostPopularMovies: PopularTVShowsResult
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 10.dp
                )
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = mostPopularMovies.first_air_date,
            )
        }
    }
}

@Composable
fun DescriptionTV(
    modifier: Modifier = Modifier,
    mostPopularMovies: PopularTVShowsResult
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 10.dp
                )
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = mostPopularMovies.original_title,
            )
        }
    }
}