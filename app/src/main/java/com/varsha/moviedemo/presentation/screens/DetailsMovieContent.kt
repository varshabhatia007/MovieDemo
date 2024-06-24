package com.varsha.moviedemo.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.varsha.moviedemo.R
import com.varsha.moviedemo.domain.mapper.GenreDomain
import com.varsha.moviedemo.ui.theme.Purple80

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsMovieContent(
    onClickBack: () -> Unit,
    title: String,
    description: String,
    imageBackdrop: String,
    imagePoster: String,
    genres: List<GenreDomain>,
    releaseDate: String,
    voteAverage: String,
    runtime: String,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.details)) },
                backgroundColor = Purple80,
                navigationIcon = {
                    IconButton(
                        onClick = { onClickBack() },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {

                Box {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        ),
                    ) {
                        Box {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(210.dp),
                                model = imageBackdrop,
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                            )

                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .offset(x = 310.dp, y = 178.dp)
                                    .width(54.dp)
                                    .height(24.dp)
                                    .background(
                                        color = Color(0x52252836),
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                                    .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Transparent
                                ),
                            ) {
                                Row {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_star),
                                        contentDescription = null,
                                        tint = Color.Yellow,
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = voteAverage,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.Yellow
                                    )
                                }
                            }
                        }
                    }

                    androidx.compose.material3.Card(
                        modifier = Modifier
                            .offset(x = 29.dp, y = 150.dp)
                            .width(95.dp)
                            .height(120.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(210.dp),
                            model = imagePoster,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                        )
                    }

                    Text(
                        modifier = Modifier
                            .width(210.dp)
                            .height(48.dp)
                            .offset(x = 140.dp, y = 220.dp),
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                    )

                }

                Spacer(modifier = Modifier.height(75.dp))

                HorizontalThreeOptions(
                    yearRelease = releaseDate,
                    duration = runtime,
                    genre = if (genres.firstOrNull() == null) "" else genres.firstOrNull()?.name.toString()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    text = "Description",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    text = description,
                    textAlign = TextAlign.Justify,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                )

                Spacer(modifier = Modifier.height(24.dp))

                val listGenres = genres.map { it.name }.joinToString(separator = " * ")

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    text = listGenres,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(600),
                )
            }
        })
}

@Composable
fun HorizontalThreeOptions(
    yearRelease: String,
    duration: String,
    genre: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Year
        Text(
            text = yearRelease,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Duration
        Text(
            text = duration,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_ticket),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Genre
        Text(
            text = genre,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

    }

}


@Preview
@Composable
fun DetailsMovieContentPrev() {
    DetailsMovieContent(
        title = "Inside Out 2",
        description = "Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new Emotions! Joy, Sadness, Anger, Fear and Disgust, who’ve long been running a successful operation by all accounts, aren’t sure how to feel when Anxiety shows up. And it looks like she’s not alone.",
        imageBackdrop = "https://image.tmdb.org/t/p/w500/xg27NrXi7VXCGUr7MG75UqLl6Vg.jpg",
        imagePoster = "https://image.tmdb.org/t/p/w500/oxxqiyWrnM0XPnBtVe9TgYWnPxT.jpg",
        genres = listOf(GenreDomain(name = "Animation")),
        releaseDate = "2024-11-06",
        voteAverage = "7.78",
        runtime = "97",
        onClickBack = {},
    )
}