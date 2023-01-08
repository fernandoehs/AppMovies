package com.example.movieappcompose.presentation.movie_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.domain.model.top_rated.TopRatedMovies
import com.example.movieappcompose.util.Constants


@Composable
fun CastDetails(
    creditsResponse: TopRatedMovies?,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cast",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    )
            )
        }


        LazyRow(content = {
            items(creditsResponse?.cast!!) { cast ->
                cast.name?.let {
                    CastItem(
                        size = 90.dp,
                        castImageUrl = "${Constants.IMAGE_BASE_UR}/${cast.profile_path}",
                        castName = it
                    )
                }
            }
        })
    }
}