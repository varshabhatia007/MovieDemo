package com.varsha.moviedemo.presentation.usecases

import com.varsha.moviedemo.data.repository.MoviesRepository
import com.varsha.moviedemo.domain.mapper.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(
        query: String,
        apiKey: String,
        language: String
    ) = moviesRepository.searchMovie(query, apiKey, language).map {
        it.results.toDomainModel()
    }
}
