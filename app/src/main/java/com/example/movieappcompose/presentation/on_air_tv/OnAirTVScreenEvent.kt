package com.example.movieappcompose.presentation.on_air_tv


sealed class OnAirTVScreenEvent {
    object OnSearchClick: OnAirTVScreenEvent()
    object OnMoviesTabClick: OnAirTVScreenEvent()
    object OnTVShowsClick: OnAirTVScreenEvent()
    object OnTrailersClick: OnAirTVScreenEvent()
}
