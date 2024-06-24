package com.varsha.moviedemo.usecases.data

import com.varsha.moviedemo.data.repository.MoviesRepository
import com.varsha.moviedemo.domain.MoviesDetailResponse
import com.varsha.moviedemo.domain.PopularsMovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response.error

class RepositoryErrorApi : MoviesRepository {
    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<PopularsMovieResponse> {
        return flow {
            throw HttpException(
                error<PopularsMovieResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): Flow<MoviesDetailResponse> {
        return flow {
            throw HttpException(
                error<MoviesDetailResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        return flow {
            throw HttpException(
                error<MoviesDetailResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }
}