package com.example.movies.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.presentation.screens.home.component.CategoryItem
import com.example.movies.presentation.screens.home.component.MySearchBar
import com.example.movies.presentation.components.MovieCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClicked: (movieId: Int, language: String) -> Unit
) {
    val genresUiState by viewModel.genresUiState.collectAsState()
    val mostSearchedUiState by viewModel.mostSearchedUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
    ) {
        Text(
            text = stringResource(R.string.search_for_a_content),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 40.dp, bottom = 8.dp)
        )

        MySearchBar()

        Spacer(modifier = Modifier.height(27.dp))

        when (genresUiState) {
            is GenresUiState.Loading -> {
                CircularProgressIndicator(color = Color.Gray)
            }

            is GenresUiState.Error -> {
                Text(
                    text = "Error: ${(genresUiState as GenresUiState.Error).message}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            is GenresUiState.Success -> {
                val genres = (genresUiState as GenresUiState.Success).genres

                Text(
                    text = stringResource(R.string.categories),
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(modifier = Modifier.height(7.dp))

                LazyRow {
                    items(genres) { genre ->
                        CategoryItem(genre.name)
                    }
                }

                Spacer(modifier = Modifier.height(27.dp))
            }

            else -> {}
        }

        when (mostSearchedUiState) {
            is MostSearchedUiState.Loading -> {
                CircularProgressIndicator(color = Color.Gray)
            }

            is MostSearchedUiState.Error -> {
                Text(
                    text = "Error: ${(mostSearchedUiState as MostSearchedUiState.Error).message}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            is MostSearchedUiState.Success -> {
                val mostSearched = (mostSearchedUiState as MostSearchedUiState.Success).mostSearched

                Text(
                    text = stringResource(R.string.most_searched),
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(mostSearched) { movie ->
                        MovieCard(
                            movieDetails = movie,
                            onMovieClicked = { onMovieClicked(movie.id, movie.originalLanguage) }
                        )
                    }
                }
            }

            else -> {}
        }
    }
}