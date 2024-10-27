package com.example.movies.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.presentation.screens.home.component.CategoryItem
import com.example.movies.presentation.screens.home.component.CustomSearchBar
import com.example.movies.presentation.screens.home.component.MovieCard
import com.example.movies.presentation.screens.home.component.MovieDetails
import com.example.movies.presentation.theme.MoviesTheme

@Composable
fun HomeScreen(
    categories: List<String>,
    mostSearched: List<MovieDetails>,
    onMovieClicked: () -> Unit
) {
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
        CustomSearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            hideKeyboard = false,
            onFocusClear = {},
        )
        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = stringResource(R.string.categories),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(7.dp))

        LazyRow {
            items(categories) { category ->
                CategoryItem(category)
            }
        }

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = stringResource(R.string.most_searched),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(mostSearched.size) {
                MovieCard(
                    movieDetails = mostSearched[it],
                    onMovieClicked = onMovieClicked
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MoviesTheme {
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

                )
        ){}
    }
}