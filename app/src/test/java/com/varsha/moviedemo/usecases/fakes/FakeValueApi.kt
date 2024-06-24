package com.varsha.moviedemo.usecases.fakes

import com.varsha.moviedemo.domain.MovieDetail
import com.varsha.moviedemo.domain.PopularsMovieResponse

object FakeValueApi {

    fun popularMovieFakeResponse() = PopularsMovieResponse(
        page = 1,
        results = listMovieEntity(),
    )

    fun listMovieEntity() = listOf(
        MovieDetail(
            id = 1,
            overview = "",
            poster_path = "",
            release_date = "",
            title = "",
            vote_average = 1.0f
        )
    )
}