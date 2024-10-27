package com.example.movies.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.ResponseState
import com.example.movies.domain.model.GenresRes
import com.example.movies.domain.model.MoviesRes
import com.example.movies.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _genresUiState = MutableStateFlow<GenresUiState>(GenresUiState.Loading)
    val genresUiState: StateFlow<GenresUiState> = _genresUiState

    private val _mostSearchedUiState = MutableStateFlow<MostSearchedUiState>(MostSearchedUiState.Loading)
    val mostSearchedUiState: StateFlow<MostSearchedUiState> = _mostSearchedUiState

    init {
        fetchGenres()
        fetchMostSearchedMovies()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            movieRepository.getGenres(language = "en")
                .collect { dataState ->
                    _genresUiState.value = when (dataState) {
                        is ResponseState.Loading -> GenresUiState.Loading
                        is ResponseState.Success -> GenresUiState.Success(dataState.data.genres)
                        is ResponseState.Error -> GenresUiState.Error(dataState.code, dataState.message)
                        is ResponseState.Empty -> GenresUiState.Empty
                    }
                }
        }
    }

    private fun fetchMostSearchedMovies() {
        viewModelScope.launch {
            movieRepository.discoverMovies(
                includeAdult = false,
                includeVideo = false,
                language = "en",
                page = 1,
                sortBy = "popularity.desc"
            ).collect { dataState ->
                _mostSearchedUiState.value = when (dataState) {
                    is ResponseState.Loading -> MostSearchedUiState.Loading
                    is ResponseState.Success -> MostSearchedUiState.Success(dataState.data.results)
                    is ResponseState.Error -> MostSearchedUiState.Error(dataState.code, dataState.message)
                    is ResponseState.Empty -> MostSearchedUiState.Empty
                }
            }
        }
    }
}

// Define the sealed classes for different UI states

sealed class GenresUiState {
    data object Loading : GenresUiState()
    data class Success(val genres: List<GenresRes.Genre>) : GenresUiState()
    data class Error(val code: Int, val message: String) : GenresUiState()
    data object Empty : GenresUiState()
}

sealed class MostSearchedUiState {
    data object Loading : MostSearchedUiState()
    data class Success(val mostSearched: List<MoviesRes.Result>) : MostSearchedUiState()
    data class Error(val code: Int, val message: String) : MostSearchedUiState()
    data object Empty : MostSearchedUiState()
}