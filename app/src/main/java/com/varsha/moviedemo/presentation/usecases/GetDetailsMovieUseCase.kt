package com.varsha.moviedemo.presentation.usecases

import com.varsha.moviedemo.data.repository.MoviesRepository
import com.varsha.moviedemo.domain.mapper.MovieDetailDomain
import com.varsha.moviedemo.domain.mapper.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailsMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(
        apiKey: String,
        language: String,
        id: String
    ) = moviesRepository.getMovieDetail(apiKey, language, id).map {
        it.toDomainModel()
    }
}

sealed class GetDetailsMovieResult {
    data class Loading(val isLoading: Boolean) : GetDetailsMovieResult()
    data class Success(val data: MovieDetailDomain) : GetDetailsMovieResult()
    data class Error(val message: String) : GetDetailsMovieResult()
    data class InternetError(val message: String) : GetDetailsMovieResult()
}

