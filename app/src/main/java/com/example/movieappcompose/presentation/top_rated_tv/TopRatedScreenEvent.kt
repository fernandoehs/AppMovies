package com.example.movieappcompose.presentation.top_rated_tv


sealed class TopRatedScreenEvent {
    object OnSearchClick: TopRatedScreenEvent()
    object OnMoviesTabClick: TopRatedScreenEvent()
    object OnTVShowsClick: TopRatedScreenEvent()
    object OnTrailersClick: TopRatedScreenEvent()
}
