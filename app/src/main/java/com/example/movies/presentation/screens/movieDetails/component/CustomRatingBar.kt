package com.example.movies.presentation.screens.movieDetails.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movies.R

@Composable
fun CustomRatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    starSize: Int = 16,
    starPadding: Int = 0,
    onRatingChanged: (Float) -> Unit = {},
) {
    var currentRating by remember { mutableFloatStateOf(rating) }

    Row(modifier = modifier) {
        for (i in 1..5) {
            val starResId = if (i <= currentRating) R.drawable.filled_star else R.drawable.unfilled_star
            Image(
                painter = painterResource(id = starResId),
                contentDescription = null,
                modifier = Modifier
                    .size(starSize.dp)
                    .clickable {
                        currentRating = i.toFloat()
                        onRatingChanged(currentRating)
                    }
                    .padding(horizontal = starPadding.dp)
            )
        }
    }
}