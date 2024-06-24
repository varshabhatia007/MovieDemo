package com.varsha.moviedemo.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varsha.moviedemo.BuildConfig
import com.varsha.moviedemo.presentation.usecases.GetDetailsMovieResult
import com.varsha.moviedemo.presentation.usecases.GetDetailsMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MovieDetailedViewModel @Inject constructor(
    private val getDetailsMovieUseCase: GetDetailsMovieUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _detailsMovie =
        MutableStateFlow<GetDetailsMovieResult>(GetDetailsMovieResult.Loading(false))
    val detailsMovie = _detailsMovie.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000, 1),
        initialValue = GetDetailsMovieResult.Loading(false)
    )

    // This is needed while user click the Movie and pass the movie Id from popular movie to open the detail screen
    init {
        val movieId = savedStateHandle.get<String>("movieId") ?: ""
        movieDetailFromId(movieId)
    }

    private fun movieDetailFromId(idMovie: String) = viewModelScope.launch {
        getDetailsMovie(idMovie).join()
    }

    private fun getDetailsMovie(id: String) = viewModelScope.launch(Dispatchers.IO) {
        getDetailsMovieUseCase.invoke(
            apiKey = BuildConfig.API_KEY,
            language = language,
            id = id
        ).onStart {
            _detailsMovie.value = GetDetailsMovieResult.Loading(true)
            delay(1.seconds) // See loading spinner for 1 second while searching
        }.onEach {
            _detailsMovie.value = GetDetailsMovieResult.Success(it)
        }.catch {
            _detailsMovie.value = GetDetailsMovieResult.Error("Error, ${it.message}")
        }.launchIn(viewModelScope)
    }

    companion object {
        const val language = "en-US"
    }
}