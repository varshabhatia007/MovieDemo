package com.varsha.moviedemo.data.repository

import com.varsha.moviedemo.data.remote.MoviesRemoteDataSource
import com.varsha.moviedemo.domain.MoviesDetailResponse
import com.varsha.moviedemo.domain.PopularsMovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MoviesRepository {
    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<PopularsMovieResponse>

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): Flow<MoviesDetailResponse>

    suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String,
    ): Flow<PopularsMovieResponse>

}

class MoviesRepositoryImpl @Inject constructor(
    private val remote: MoviesRemoteDataSource,
) : MoviesRepository {

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = remote.getPopularMovies(apiKey, language, page)

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = remote.getMovieDetail(apiKey, language, id)

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        return remote.searchMovie(query, apiKey, language)
    }
}