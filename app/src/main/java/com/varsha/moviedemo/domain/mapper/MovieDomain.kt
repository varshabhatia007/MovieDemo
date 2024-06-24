package com.varsha.moviedemo.domain.mapper

import com.varsha.moviedemo.BuildConfig
import com.varsha.moviedemo.domain.MovieDetail

data class MovieDomain(
    val id: Int,
    val posterPath: String,
    val overview: String,
    val title: String,
    val voteAverage: Float,
    val releaseDate: String? = null,
)

//Mapper to Domain

fun List<MovieDetail>.toDomainModel(): List<MovieDomain> {
    return map {
        MovieDomain(
            id = it.id,
            posterPath = BuildConfig.IMAGE_URL + it.poster_path,
            overview = it.overview,
            title = it.title,
            voteAverage = it.vote_average,
            releaseDate = it.release_date
        )
    }
}