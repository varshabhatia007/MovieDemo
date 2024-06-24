package com.varsha.moviedemo.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.varsha.moviedemo.domain.mapper.MovieDetailDomain
import com.varsha.moviedemo.presentation.composables.ErrorScreen
import com.varsha.moviedemo.presentation.composables.NoInternetConnectionScreen
import com.varsha.moviedemo.presentation.composables.LoadingScreen
import com.varsha.moviedemo.presentation.usecases.GetDetailsMovieResult

@Composable
fun DetailsMovieScreen(
    navController: NavController,
    stateMovieDetail: GetDetailsMovieResult
) {
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var isInternetError by remember { mutableStateOf(false) }

    var item by remember { mutableStateOf(MovieDetailDomain()) }


    LaunchedEffect(key1 = stateMovieDetail) {
        when (stateMovieDetail) {
            is GetDetailsMovieResult.Success -> {
                isLoading = false
                isError = false
                isInternetError = false
                isSuccess = true
                item = stateMovieDetail.data
            }

            is GetDetailsMovieResult.Error -> {
                isLoading = false
                isSuccess = false
                isError = true
                isInternetError = false
            }

            is GetDetailsMovieResult.Loading -> {
                isError = false
                isSuccess = false
                isInternetError = true
                isLoading = stateMovieDetail.isLoading
            }

            is GetDetailsMovieResult.InternetError -> {
                isLoading = false
                isError = false
                isInternetError = true
                isSuccess = false
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                LoadingScreen()
            }

            isError -> {
                ErrorScreen()
            }

            isInternetError -> {
                NoInternetConnectionScreen()
            }

            isSuccess -> {
                DetailsMovieContent(
                    onClickBack = {
                        navController.popBackStack()
                    },
                    title = item.title ?: "",
                    description = item.overview ?: "",
                    imageBackdrop = item.backdropPath ?: "",
                    imagePoster = item.posterPath ?: "",
                    genres = item.genres ?: listOf(),
                    releaseDate = item.releaseDate ?: "",
                    voteAverage = item.voteAverage?.toString() ?: "",
                    runtime = item.runtimeWithMinutes ?: "",
                )
            }
        }
    }
}

