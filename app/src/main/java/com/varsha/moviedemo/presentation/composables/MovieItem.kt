package com.varsha.moviedemo.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.varsha.moviedemo.ui.theme.Purple40

@Composable
fun MovieItem(
    title: String,
    description: String,
    imageUrl: String,
    rating: Float,
    releaseDate: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .align(CenterVertically)
                    .fillMaxWidth(0.3f),
                shape = RoundedCornerShape(0.dp),
            ) {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                // TODO need to update the release date format
                Text(text = releaseDate)

                Spacer(modifier = Modifier.height(8.dp))
                RatingBar(
                    numOfStars = 10,
                    size = 10.dp,
                    stepSize = StepSize.ONE,
                    value = rating,
                    style = RatingBarStyle.Fill(
                        activeColor = Purple40,
                        inActiveColor = Gray.copy(alpha = 0.5f),
                    ),
                    onValueChange = {},
                    onRatingChanged = {})

            }
        }

    }
}

@Preview
@Composable
fun HorizontalMovieItemPrev() {
    MovieItem(
        title = "Inside Out 2",
        description = "Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new Emotions! Joy, Sadness, Anger, Fear and Disgust, who’ve long been running a successful operation by all accounts, aren’t sure how to feel when Anxiety shows up. And it looks like she’s not alone.",
        imageUrl = "https://image.tmdb.org/t/p/w500/xg27NrXi7VXCGUr7MG75UqLl6Vg.jpg",
        rating = 7.775f,
        onClick = {},
        releaseDate = "2024-06-11"
    )
}