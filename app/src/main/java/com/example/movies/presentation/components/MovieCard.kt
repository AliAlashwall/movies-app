package com.example.movies.presentation.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movies.R
import com.example.movies.domain.model.MoviesRes
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.outline
import com.example.movies.util.Constants.IMAGES_BASE


@Composable
fun MovieCard(
    movieDetails: MoviesRes.Result,
    context: Context = LocalContext.current,
    onMovieClicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .width(124.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .clickable { onMovieClicked() }
                .size(124.dp)
                .border(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(24.dp),
                    width = 0.dp
                ),
            model = ImageRequest.Builder(context = context)
                .data(IMAGES_BASE + movieDetails.backdropPath).crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator()
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.movie_card),
                    contentDescription = null
                )
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movieDetails.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = movieDetails.voteAverage.toString().take(3),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            textAlign = TextAlign.Center,
            color = outline
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MovieCardPreview() {
    MoviesTheme {
        MovieCard(
            movieDetails = MoviesRes.Result(
                adult = false,
                backdropPath = "/path/to/backdrop.jpg",
                genreIds = listOf(1, 2, 3), // Assuming these IDs correspond to certain genres
                id = 12345,
                originalLanguage = "en",
                originalTitle = "Mock Movie Title",
                overview = "This is a mock overview of the movie.",
                popularity = 10.5,
                posterPath = "/path/to/poster.jpg",
                releaseDate = "2024-10-27",
                title = "Mock Movie",
                video = false,
                voteAverage = 7.5,
                voteCount = 1500
            )
        )
    }
}