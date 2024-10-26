package com.example.movies.presentation.screens.movieDetails.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.presentation.theme.MoviesTheme

@Composable
fun ActorCard(
    actorName: String = "Maria Espaes",
    actorRole: String = "As Morbiuds",
    actorImage: Int = R.drawable.person
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp).height(52.dp)
    ) {
        Box {
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF5E5E5E),
                            shape = MaterialTheme.shapes.large
                        )
                        .padding(
                            start = 58.dp,
                            end = 30.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                ) {
                    Text(
                        text = actorName,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(82.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = actorRole,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = 2.dp).width(53.dp),
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }

            Card(
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Image(
                    painter = painterResource(id = actorImage),
                    contentDescription = "Actor Image",
                    modifier = Modifier.size(52.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActorCardPreview() {
    MoviesTheme {
        ActorCard()
    }
}