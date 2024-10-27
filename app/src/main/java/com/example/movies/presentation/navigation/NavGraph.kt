package com.example.movies.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.R
import com.example.movies.presentation.screens.home.HomeScreen
import com.example.movies.presentation.screens.home.component.MovieDetails
import com.example.movies.presentation.screens.movieDetails.ActorDetails
import com.example.movies.presentation.screens.movieDetails.MovieDetailsScreen
import com.example.movies.presentation.screens.onboarding.OnboardingScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Onboarding.name) {
        composable(route = Screens.Onboarding.name) {
            OnboardingScreen(onEnterClicked = { navController.navigate(Screens.Home.name) })
        }
        composable(route = Screens.Home.name) {
            HomeScreen(
                categories = listOf("Drama", "Action", "Comedy", "Horror", "Romance"),
                mostSearched = listOf(
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),

                ),
                onMovieClicked = { navController.navigate(Screens.MovieDetails.name) }
            )

        }
        composable(
            route = Screens.MovieDetails.name,
        ) {
            MovieDetailsScreen(
                rating = 5f, actors = listOf(
                    ActorDetails("Jared Leto", "Dr. Michael Morbius", R.drawable.person),
                    ActorDetails("Matt Smith", "Loxias Crown", R.drawable.person),
                    ActorDetails("Adria Arjona", "Martine Bancroft", R.drawable.person),
                    ActorDetails("Jared Harris", "Morbius' mentor", R.drawable.person),
                ),
                mostSearched = listOf(
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    ),
                    MovieDetails(
                        title = "Movie Title",
                        image = R.drawable.movie_card,
                        releasedYear = "2022"
                    )
                ),
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}