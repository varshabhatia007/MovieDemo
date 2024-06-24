package com.varsha.moviedemo.domain

data class PopularsMovieResponse(
    val page: Int,
    val results: List<MovieDetail>
)

data class MovieDetail(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
    val release_date: String? = null,
)