package com.example.movies.presentation.screens.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.ResponseState
import com.example.movies.domain.model.MovieDetailsRes
import com.example.movies.domain.model.MoviesRes
import com.example.movies.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieDetailsState =
        MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailsState: StateFlow<MovieDetailsUiState> get() = _movieDetailsState

    private val _mostSearchedState =
        MutableStateFlow<MostSearchedUiState>(MostSearchedUiState.Loading)
    val mostSearchedState: StateFlow<MostSearchedUiState> get() = _mostSearchedState

    fun fetchMovieDetails(movieId: Int, language: String) {
        viewModelScope.launch {
            movieRepository.getMovieDetails(movieId, language).collect { dataState ->
                when (dataState) {
                    is ResponseState.Success -> {
                        _movieDetailsState.value = MovieDetailsUiState.Success(dataState.data)
                    }

                    is ResponseState.Error -> {
                        _movieDetailsState.value = MovieDetailsUiState.Error(dataState.message)
                    }

                    ResponseState.Loading -> {
                        _movieDetailsState.value = MovieDetailsUiState.Loading
                    }

                    ResponseState.Empty -> {
                        _movieDetailsState.value = MovieDetailsUiState.Empty
                    }
                }
            }
        }
    }

    fun fetchMostSearchedMovies(language: String, page: Int = 1) {
        viewModelScope.launch {
            movieRepository.discoverMovies(
                includeAdult = false,
                includeVideo = false,
                language = language,
                page = page,
                sortBy = "popularity.desc"
            ).collect { dataState ->
                when (dataState) {
                    is ResponseState.Success -> {
                        _mostSearchedState.value =
                            MostSearchedUiState.Success(dataState.data.results) // Assuming results is a List<MoviesRes.Result>
                    }

                    is ResponseState.Error -> {
                        _mostSearchedState.value = MostSearchedUiState.Error(dataState.message)
                    }

                    ResponseState.Loading -> {
                        _mostSearchedState.value = MostSearchedUiState.Loading
                    }

                    ResponseState.Empty -> {
                        _mostSearchedState.value = MostSearchedUiState.Empty
                    }
                }
            }
        }
    }
}

sealed class MostSearchedUiState {
    data object Loading : MostSearchedUiState()
    data class Success(val movies: List<MoviesRes.Result>) : MostSearchedUiState()
    data class Error(val message: String) : MostSearchedUiState()
    data object Empty : MostSearchedUiState()
}

sealed class MovieDetailsUiState {
    data object Loading : MovieDetailsUiState()
    data class Success(val movieDetails: MovieDetailsRes) : MovieDetailsUiState()
    data class Error(val message: String) : MovieDetailsUiState()
    data object Empty : MovieDetailsUiState()
}
