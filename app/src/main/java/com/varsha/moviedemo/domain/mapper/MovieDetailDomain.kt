package com.varsha.moviedemo.domain.mapper

import com.varsha.moviedemo.BuildConfig
import com.varsha.moviedemo.domain.Genre
import com.varsha.moviedemo.domain.MoviesDetailResponse

data class MovieDetailDomain(
    val backdropPath: String? = null,
    val genres: List<GenreDomain>? = null,
    val id: Int? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val runtimeWithMinutes: String? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
)

data class GenreDomain(
    val id: Int? = null,
    val name: String? = null,
)

//Mapper to Domain

fun MoviesDetailResponse.toDomainModel(): MovieDetailDomain {
    return MovieDetailDomain(
        backdropPath = BuildConfig.IMAGE_URL + this.backdrop_path,
        genres = this.genres?.toDomainGenre(),
        id = this.id,
        overview = this.overview,
        posterPath = BuildConfig.IMAGE_URL + this.poster_path,
        releaseDate = this.release_date,
        runtimeWithMinutes = "${this.runtime}",
        title = this.title,
        voteAverage = String.format("%.2f",this.vote_average).toDouble(),
    )
}

fun List<Genre>.toDomainGenre(): List<GenreDomain> {
    return this.map {
        GenreDomain(
            id = it.id,
            name = it.name
        )
    }
}