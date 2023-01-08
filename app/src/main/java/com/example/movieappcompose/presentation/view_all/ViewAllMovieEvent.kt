package com.example.movieappcompose.presentation.view_all


sealed class ViewAllMovieEvent {
    object OnSearchClick: ViewAllMovieEvent()
    object OnMoviesTabClick: ViewAllMovieEvent()
    object OnTVShowsClick: ViewAllMovieEvent()
    object OnTrailersClick: ViewAllMovieEvent()
}
