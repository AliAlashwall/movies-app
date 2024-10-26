package com.example.movies.presentation.screens.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.presentation.screens.home.component.MovieCard
import com.example.movies.presentation.screens.home.component.MovieDetails
import com.example.movies.presentation.screens.movieDetails.component.ActorCard
import com.example.movies.presentation.screens.movieDetails.component.CustomRatingBar
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.primary

data class ActorDetails(
    val name: String,
    val role: String,
    val image: Int,
)

@Composable
fun MovieDetailsScreen(
    rating: Float, numberOfRatingUsers: Int = 342,
    actors: List<ActorDetails>,
    mostSearched: List<MovieDetails>
) {
    Column(Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.movie_1),
                contentDescription = stringResource(R.string.movie_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillBounds
            )

            Card(
                colors = CardDefaults.cardColors(primary),
                modifier = Modifier
                    .padding(start = 20.dp, top = 44.dp)
                    .size(35.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = "back",
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 11.dp)
                            .size(24.dp)
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(7.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 17.dp, end = 27.dp),

            ) {
            Column {
                Text(
                    text = "Morbius",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                )
                Text(
                    text = "Marvel Studios",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 2.dp)
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                CustomRatingBar(
                    rating = rating,
                    modifier = Modifier
                )
                Text(
                    text = "From $numberOfRatingUsers users",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex...",
            style = MaterialTheme.typography.titleSmall,
            color = Color(0xFF8F8F8F),
            modifier = Modifier.padding(start = 17.dp, end = 32.dp, top = 17.dp, bottom = 23.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(start = 17.dp, end = 14.dp)
        ) {
            items(actors.size) { index ->
                val actor = actors[index]
                ActorCard(
                    actorName = actor.name,
                    actorRole = actor.role,
                    actorImage = actor.image
                )

                if (index != actors.size - 1) {
                    Spacer(modifier = Modifier.width(17.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.most_searched),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 17.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        LazyRow(modifier = Modifier.padding(horizontal = 17.dp)) {
            items(mostSearched.size) {
                MovieCard(
                    movieDetails = mostSearched[it],
//                    cardWidth = 100.dp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MoviesTheme {
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
            )
        )
    }
}