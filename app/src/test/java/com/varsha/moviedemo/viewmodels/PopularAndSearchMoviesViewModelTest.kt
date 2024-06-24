package com.varsha.moviedemo.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.varsha.moviedemo.domain.mapper.toDomainModel
import com.varsha.moviedemo.usecases.utils.MainCoroutineScopeRule
import com.varsha.moviedemo.usecases.data.RepositorySuccessApi
import com.varsha.moviedemo.usecases.fakes.FakeValueApi
import com.varsha.moviedemo.presentation.usecases.GetPopularMoviesUseCase
import com.varsha.moviedemo.presentation.usecases.PopularMoviesResult
import com.varsha.moviedemo.presentation.usecases.SearchMovieUseCase
import com.varsha.moviedemo.presentation.viewmodels.PopularAndSearchMoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularAndSearchMoviesViewModelTest {

    private lateinit var repositorySuccess: RepositorySuccessApi
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var searchMoviesUseCase: SearchMovieUseCase
    private lateinit var sut: PopularAndSearchMoviesViewModel

    private val listResult = mutableListOf<PopularMoviesResult>()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositorySuccess = RepositorySuccessApi()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(repositorySuccess)
        searchMoviesUseCase = SearchMovieUseCase(repositorySuccess)

        sut = PopularAndSearchMoviesViewModel(getPopularMoviesUseCase, searchMoviesUseCase)
    }

    @Test
    fun `get popular movies should return different results emitted`() =
        mainCoroutineScopeRule.runBlockingTest {
            // Arrange
            val scope = launch {
                sut.movies.collect {
                    listResult.add(it)
                }
            }
            // Act
            sut.getPopularMovies()
            // Assert -> expected = actual
            val expected = listOf(
                PopularMoviesResult.Loading(true),
                PopularMoviesResult.Loading(false),
            )

            scope.cancel()
            assertNotEquals(expected, listResult)
        }

    @Test
    fun `get popular test first emit should be loading`() = runTest {
        // Arrange
        sut.movies.first {
            listResult.add(it)
        }
        // Act
        sut.getPopularMovies()
        // Assert
        val expected = PopularMoviesResult.Loading(true)
        assertEquals(expected, listResult.first())
    }

    @Test
    fun `get popular normal test should return success`() = runTest {
        // Arrange
        val list = sut.movies.take(2).toList()
        // Act
        sut.getPopularMovies()
        // Assert
        val expected = listOf(
            PopularMoviesResult.Loading(true),
            PopularMoviesResult.Success(FakeValueApi.listMovieEntity().toDomainModel()),
        )
        assertEquals(expected, list)
    }

    @After
    fun tearDown() {
        listResult.clear()
        mainCoroutineScopeRule.coroutineContext.cancelChildren()
    }
}