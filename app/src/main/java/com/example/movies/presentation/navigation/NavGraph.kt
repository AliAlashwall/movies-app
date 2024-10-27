package com.example.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movies.presentation.components.NoInternetConnection
import com.example.movies.presentation.screens.home.HomeScreen
import com.example.movies.presentation.screens.movieDetails.MovieDetailsScreen
import com.example.movies.presentation.screens.onboarding.OnboardingScreen
import com.example.movies.util.ConnectivityObserver

@Composable
fun NavGraph(
    connectionState: ConnectivityObserver.Status,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Onboarding.name) {
        composable(route = Screens.Onboarding.name) {
            OnboardingScreen(onEnterClicked = { navController.navigate(Screens.Home.name) })
        }
        composable(route = Screens.Home.route) {
            HomeScreen(
                onMovieClicked = { movieId, language ->
                    navController.navigate(Screens.getMovieDetailsRoute(movieId, language))
                }
            )
        }
        composable(
            route = Screens.MovieDetails.route,
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType },
                navArgument("language") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            val language = backStackEntry.arguments?.getString("language") ?: ""

            MovieDetailsScreen(
                movieId = movieId,
                language = language,
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
    if (connectionState == ConnectivityObserver.Status.Lost || connectionState == ConnectivityObserver.Status.Unavailable) {
        NoInternetConnection()
        return
    }
}