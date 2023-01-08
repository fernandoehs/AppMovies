package com.example.movieappcompose.presentation.movie_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter


@Composable
fun CastItem(
    size: Dp,
    castName: String,
    castImageUrl: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberImagePainter(
                data = castImageUrl,
                builder = {
                    placeholder(com.example.movieappcompose.R.drawable.ic_placeholder)
                    crossfade(true)
                }
            ),
            modifier = Modifier
                .fillMaxSize()
                .size(size)
                .padding(8.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            contentDescription = "Character"
        )

        Text(
            text = castName,
            color = Color.LightGray,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 11.sp
        )
    }
}

