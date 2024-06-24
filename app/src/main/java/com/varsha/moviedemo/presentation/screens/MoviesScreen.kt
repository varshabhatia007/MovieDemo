package com.varsha.moviedemo.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.varsha.moviedemo.presentation.composables.EmptySearchScreen
import com.varsha.moviedemo.presentation.composables.ErrorScreen
import com.varsha.moviedemo.presentation.composables.NoInternetConnectionScreen
import com.varsha.moviedemo.presentation.composables.MovieItem
import com.varsha.moviedemo.presentation.composables.LoadingScreen
import com.varsha.moviedemo.presentation.usecases.PopularMoviesResult
import com.varsha.moviedemo.R
import com.varsha.moviedemo.domain.mapper.MovieDomain
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    moviesList: PopularMoviesResult,
    onClickNavigateToDetails: (Int) -> Unit,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        var searchQuery by rememberSaveable { mutableStateOf("") }

        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(40.dp)),
            query = searchQuery,
            onQueryChange = { queryChanged ->
                searchQuery = queryChanged
                onQueryChange(queryChanged)
            },
            onSearch = {},
            active = true,
            onActiveChange = {},
            placeholder = { Text(stringResource(R.string.search_movie_from_here)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        ) {
        }

        Spacer(modifier = Modifier.height(16.dp))

        HeaderMoviesScreen(
            searchQuery = searchQuery,
            onClickNavigateToDetails = onClickNavigateToDetails,
            popularMoviesState = moviesList
        )


    }
}

@Composable
fun HeaderMoviesScreen(
    searchQuery: String,
    onClickNavigateToDetails: (Int) -> Unit,
    popularMoviesState: PopularMoviesResult
) {
    var isErrorGeneral by rememberSaveable { mutableStateOf(false) }
    var isSuccess by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var isEmpty by rememberSaveable { mutableStateOf(false) }
    var isInternetError by rememberSaveable { mutableStateOf(false) }

    var popularMoviesList by rememberSaveable { mutableStateOf(listOf<MovieDomain>()) }

    LaunchedEffect(key1 = popularMoviesState) {
        when (popularMoviesState) {
            PopularMoviesResult.Empty -> {
                isLoading = false
                isErrorGeneral = false
                isInternetError = false
                isEmpty = true
            }

            is PopularMoviesResult.Error -> {
                isLoading = false
                isErrorGeneral = true
            }

            PopularMoviesResult.InternetError -> {
                isErrorGeneral = false
            }

            is PopularMoviesResult.Loading -> {
                isLoading = popularMoviesState.isLoading
                isErrorGeneral = false
            }

            is PopularMoviesResult.Success -> {
                isLoading = false
                isErrorGeneral = false
                isEmpty = false
                isInternetError = false
                isSuccess = true
                popularMoviesList = popularMoviesState.list
            }
        }
    }

    when {
        isLoading -> {
            LoadingScreen()
        }

        isErrorGeneral -> {
            ErrorScreen(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }

        isInternetError -> {
            NoInternetConnectionScreen(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }

        isEmpty -> {
            EmptySearchScreen(
                Modifier.padding(bottom = 180.dp),
                description = stringResource(
                    id = R.string.empty_screen_description_no_results,
                    searchQuery
                )
            )
        }

        isSuccess -> {
            LazyColumn(
                content = {
                    items(popularMoviesList) {

                        MovieItem(
                            title = it.title,
                            description = it.overview,
                            imageUrl = it.posterPath,
                            rating = it.voteAverage,
                            releaseDate = it.releaseDate ?: "",
                            onClick = { onClickNavigateToDetails(it.id) })

                        if (it == popularMoviesList.last()) {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                })
        }
    }


}


@Preview
@Composable
fun MoviesScreenPrev() {
    val moviesTests = listOf<MovieDomain>(
        MovieDomain(
            id = 1,
            title = "Movie 1 Title",
            overview = "Movie 1 Overview",
            posterPath = "https://image.tmdb.org/t/p/original/xg27NrXi7VXCGUr7MG75UqLl6Vg.jpg",
            voteAverage = 7.755f,
            releaseDate = "2024-11-06"
        ),
        MovieDomain(
            id = 2,
            title = "Movie 2 Title",
            overview = "Movie 2 Overview",
            posterPath = "https://image.tmdb.org/t/p/original/xg27NrXi7VXCGUr7MG75UqLl6Vg.jpg",
            voteAverage = 7.4f,
            releaseDate = "2024-06-24"
        ),
    )

    MoviesScreen(
        moviesList = PopularMoviesResult.Success(moviesTests),
        onClickNavigateToDetails = {
            Timber.d("onClickNavigateToDetails: $it")
        },
        onQueryChange = {
            Timber.d("onQueryChange: $it")
        }
    )
}