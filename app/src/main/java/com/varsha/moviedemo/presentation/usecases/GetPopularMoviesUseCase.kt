package com.varsha.moviedemo.presentation.usecases

import com.varsha.moviedemo.data.repository.MoviesRepository
import com.varsha.moviedemo.domain.mapper.MovieDomain
import com.varsha.moviedemo.domain.mapper.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(
        apiKey: String,
        language: String,
        page: Int
    ) = moviesRepository.getPopularMovies(apiKey, language, page).map {
        it.results.toDomainModel()
    }
}

sealed class PopularMoviesResult {
    data class Loading(val isLoading: Boolean) : PopularMoviesResult()
    data class Success(val list: List<MovieDomain>) : PopularMoviesResult()
    data class Error(val error: String) : PopularMoviesResult()
    data object InternetError : PopularMoviesResult()
    data object Empty : PopularMoviesResult()
}
