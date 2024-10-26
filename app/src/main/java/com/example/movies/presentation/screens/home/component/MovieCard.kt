package com.example.movies.presentation.screens.home.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movies.R
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.outline

data class MovieDetails(
    val title: String,
    val image: Int,
    val releasedYear: String,
)

@Composable
fun MovieCard(
    movieDetails: MovieDetails,
    cardWidth: Dp = 124.dp,
    context: Context = LocalContext.current
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .width(cardWidth)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(124.dp)
                .border(color = Color.Transparent, shape = RoundedCornerShape(24.dp), width = 0.dp),
            model = ImageRequest.Builder(context = context)
                .data(movieDetails.image).crossfade(true)
                .build(),
            loading = {
                Image(
                    painter = painterResource(id = R.drawable.movie_card),
                    contentDescription = null
                )
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
            textAlign = TextAlign.Center
        )
        Text(
            text = movieDetails.releasedYear,
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
            movieDetails = MovieDetails(
                title = "Movie Title",
                image = R.drawable.movie_card,
                releasedYear = "2022"
            )
        )
    }
}