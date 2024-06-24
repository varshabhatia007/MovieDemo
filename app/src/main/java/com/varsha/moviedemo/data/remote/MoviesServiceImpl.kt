package com.varsha.moviedemo.data.remote

import com.varsha.moviedemo.domain.MoviesDetailResponse
import com.varsha.moviedemo.domain.PopularsMovieResponse
import com.varsha.moviedemo.network.NetworkResult
import javax.inject.Inject

interface MoviesService {
    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): PopularsMovieResponse

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): MoviesDetailResponse

    suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String,
    ): PopularsMovieResponse
}


class MoviesServiceImpl @Inject constructor(
    private val moviesAPIService: MoviesAPIService
) : NetworkResult(), MoviesService {
    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = getResult(
        call = {
            moviesAPIService.getPopularMovies(
                apiKey = apiKey,
                language = language,
                page = page
            )
        },
        forceError = false
    )

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = getResult(
        call = {
            moviesAPIService.getMovieDetail(
                apiKey = apiKey,
                language = language,
                id = id
            )
        },
        forceError = false
    )

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ) = getResult(
        call = {
            moviesAPIService.searchMovie(
                query = query,
                apiKey = apiKey,
                language = language
            )
        },
        forceError = false
    )
}