package com.varsha.moviedemo.usecases.data

import com.varsha.moviedemo.data.repository.MoviesRepository
import com.varsha.moviedemo.domain.MoviesDetailResponse
import com.varsha.moviedemo.domain.PopularsMovieResponse
import com.varsha.moviedemo.usecases.fakes.FakeValueApi.popularMovieFakeResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds


class RepositorySuccessApi : MoviesRepository {
    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<PopularsMovieResponse> {
        return flow {
            delay(2.seconds) // Simulate network delay
            emit(popularMovieFakeResponse())
        }
    }

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): Flow<MoviesDetailResponse> {
        return flow {
            emit(
                MoviesDetailResponse(
                    backdrop_path = "",
                    genres = listOf(),
                    id = 1,
                    overview = "",
                    poster_path = "",
                    release_date = "",
                    runtime = 1,
                    title = "",
                    vote_average = 1.0,
                )
            )
        }
    }

    override suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        return flow {
            emit(
                PopularsMovieResponse(
                    page = 1,
                    results = listOf(),
                )
            )
        }
    }
}