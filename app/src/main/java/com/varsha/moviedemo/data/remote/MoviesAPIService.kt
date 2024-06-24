package com.varsha.moviedemo.data.remote

import com.varsha.moviedemo.domain.MoviesDetailResponse
import com.varsha.moviedemo.domain.PopularsMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPIService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<PopularsMovieResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<MoviesDetailResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Response<PopularsMovieResponse>
}