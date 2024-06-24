package com.varsha.moviedemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.varsha.moviedemo.R
import com.varsha.moviedemo.presentation.navigation.Graph.MOVIE_ID
import com.varsha.moviedemo.presentation.screens.DashboardScreen
import com.varsha.moviedemo.presentation.screens.DetailsMovieScreen
import com.varsha.moviedemo.presentation.screens.MoviesScreen
import com.varsha.moviedemo.presentation.viewmodels.MovieDetailedViewModel
import com.varsha.moviedemo.presentation.viewmodels.PopularAndSearchMoviesViewModel
import timber.log.Timber

@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {
            DashboardScreen()
        }
    }
}


@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreen.MoviesHomeScreen.route,
    ) {

        composable(HomeScreen.MoviesHomeScreen.route) {
            val popularAndSearchMoviesViewModel = hiltViewModel<PopularAndSearchMoviesViewModel>()
            val moviesState by popularAndSearchMoviesViewModel.movies.collectAsStateWithLifecycle()
            MoviesScreen(
                moviesList = moviesState,
                onClickNavigateToDetails = { movieID ->
                    Timber.d("movieId: $movieID")
                    navController.navigate(route = Graph.DETAILS + "/$movieID")
                },
                onQueryChange = { query ->
                    popularAndSearchMoviesViewModel.searchMovieOrEmpty(query)
                }
            )
        }
        detailsNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS + "/{$MOVIE_ID}",
        startDestination = DetailsScreen.Information.route
    ) {

        composable(DetailsScreen.Information.route) {
            val movieId = it.arguments?.getString("movieId") ?: ""
            val movieDetailedViewModel = hiltViewModel<MovieDetailedViewModel>()
            val stateMovieDetail by movieDetailedViewModel.detailsMovie.collectAsStateWithLifecycle()
            Timber.d("MOVIE ID : ${movieId}")

            DetailsMovieScreen(
                navController = navController,
                stateMovieDetail = stateMovieDetail,
            )
        }
    }
}


sealed class DetailsScreen(val route: String) {
    data object Information : DetailsScreen("information_screen")
}

sealed class HomeScreen(val route: String, val icon: Int, val title: String) {
    data object MoviesHomeScreen :
        HomeScreen("movies_screen", R.drawable.ic_movie, "Movies")
}


object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
    const val MOVIE_ID = "movieId"
}