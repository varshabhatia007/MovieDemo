package com.varsha.moviedemo.usecases

import com.varsha.moviedemo.domain.mapper.MovieDomain
import com.varsha.moviedemo.presentation.usecases.GetPopularMoviesUseCase
import com.varsha.moviedemo.usecases.data.RepositoryErrorApi
import com.varsha.moviedemo.usecases.data.RepositorySuccessApi
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class GetPopularMoviesUseCaseTest {

    private lateinit var sut: GetPopularMoviesUseCase
    private lateinit var sutSuccess: GetPopularMoviesUseCase
    private lateinit var repositoryFailureApi: RepositoryErrorApi
    private lateinit var repositorySuccessApi: RepositorySuccessApi

    private val listMovies = mutableListOf<MovieDomain>()

    @Before
    fun setUp() {
        repositoryFailureApi = RepositoryErrorApi()
        sut = GetPopularMoviesUseCase(repositoryFailureApi)

        repositorySuccessApi = RepositorySuccessApi()
        sutSuccess = GetPopularMoviesUseCase(repositorySuccessApi)
    }

    @Test(expected = HttpException::class)
    fun `should return exception when network request is failed`() = runBlocking {
        // Arrange
        val result = sut.invoke(
            apiKey = "1234567890",
            language = "en-US",
            page = 1
        )
        //Act
        result.collect {
            listMovies += it
        }
        //Assert
        assert(listMovies.isEmpty())
    }

    @Test
    fun `should return success with list converted to domain when network request is success`() =
        runBlocking {
            // Arrange
            val result = sutSuccess.invoke(
                apiKey = "1234567890",
                language = "en-US",
                page = 1
            )
            //Act
            result.collect {
                listMovies += it
            }
            //Assert
            assert(listMovies.isNotEmpty())
        }
}