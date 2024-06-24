package com.varsha.moviedemo.domain


data class MoviesDetailResponse(
    val backdrop_path: String? = null,
    val genres: List<Genre>? = null,
    val id: Int? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val runtime: Int? = null,
    val title: String? = null,
    val vote_average: Double? = null
)

data class Genre(
    val id: Int,
    val name: String
)
