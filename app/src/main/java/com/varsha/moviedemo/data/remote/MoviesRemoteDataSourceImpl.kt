package com.varsha.moviedemo.data.remote

import com.varsha.moviedemo.domain.MoviesDetailResponse
import com.varsha.moviedemo.domain.PopularsMovieResponse
import com.varsha.moviedemo.network.performNetworkFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MoviesRemoteDataSource {
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


class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = performNetworkFlow {
        moviesService.getPopularMovies(apiKey, language, page)
    }

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = performNetworkFlow {
        moviesService.getMovieDetail(apiKey, language, id)
    }

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ) = performNetworkFlow {
        moviesService.searchMovie(query, apiKey, language)
    }
}