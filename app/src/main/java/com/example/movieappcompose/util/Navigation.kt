package com.example.movieappcompose.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieappcompose.presentation.movie_detail.MovieDetailScreen
import com.example.movieappcompose.presentation.on_air_tv.OnAirTVScreen
import com.example.movieappcompose.presentation.on_boarding.OnBoardingScreen
import com.example.movieappcompose.presentation.top_rated_tv.TopRatedTVScreen
import com.example.movieappcompose.presentation.view_all.ViewAllPopularMoviesScreen
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoardingScreen.route
    ) {
        composable(route = Screen.OnBoardingScreen.route) {
            OnBoardingScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.ViewAllPopularMoviesScreen.route
        ) {
            ViewAllPopularMoviesScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.TopRatedTVScreen.route
        ) {
            TopRatedTVScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.OnAirTVScreen.route
        ) {
            OnAirTVScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.MovieDetailScreen.route + "?tv_id={tv_id}",
            arguments = listOf(
                navArgument(name = "tv_id") {
                    type = NavType.IntType
                }
            )
        ) {
            MovieDetailScreen(navController = navController)
        }
    }
}