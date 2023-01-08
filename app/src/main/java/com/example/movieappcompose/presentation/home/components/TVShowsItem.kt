package com.example.movieappcompose.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.movieappcompose.R
import com.example.movieappcompose.domain.model.popular_tv_shows.PopularTVShowsResult
import com.example.movieappcompose.domain.model.top_rated.TopRatedMoviesResult

@ExperimentalMaterialApi
@Composable
fun TVShowsItem(
    modifier: Modifier = Modifier,
    TVShows: TopRatedMoviesResult,
    onItemClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/original${TVShows.profile_path}",
                builder = {
                    placeholder(R.drawable.ic_placeholder)
                    crossfade(true)
                }
            ),
            modifier = Modifier
                .fillMaxSize()
                .size(150.dp)
                .padding(8.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            contentDescription = "Character"
        )

        Text(
            text = TVShows.name.toString(),
            color = Color.LightGray,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 11.sp
        )
    }
}

//
//@Composable
//fun StarRatingsForTVShows(
//    modifier: Modifier = Modifier,
//    TVShows: PopularTVShowsResult
//) {
//    Box(
//        modifier = modifier
//            .background(
//                color = Color.Gray,
//                shape = RoundedCornerShape(
//                    topStart = 0.dp,
//                    topEnd = 0.dp,
//                    bottomStart = 0.dp,
//                    bottomEnd = 20.dp
//                )
//            )
//    ){
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.Center),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                imageVector = Icons.Default.StarRate,
//                contentDescription = stringResource(id = R.string.star_rate),
//                colorFilter = ColorFilter.tint(color = Color.Yellow),
//                modifier = Modifier.size(18.dp)
//            )
//            Text(
//                text = TVShows.vote_average.toString()
//            )
//        }
//    }
//}