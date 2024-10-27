package com.example.movies.domain.repository

import com.example.movies.domain.ResponseState
import com.example.movies.domain.model.GenresRes
import com.example.movies.domain.model.MovieDetailsRes
import com.example.movies.domain.model.MoviesRes
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getGenres(language: String = "en"): Flow<ResponseState<GenresRes>>
    suspend fun getMovieDetails(
        movieId: Int,
        language: String = "en-US"
    ): Flow<ResponseState<MovieDetailsRes>>

    suspend fun discoverMovies(
        includeAdult: Boolean = false,
        includeVideo: Boolean = false,
        language: String = "en-US",
        page: Int = 1,
        sortBy: String = "popularity.desc"
    ): Flow<ResponseState<MoviesRes>>

    suspend fun searchMovies(
        query: String,
        includeAdult: Boolean = false,
        language: String = "en-US",
        page: Int = 1
    ): Flow<ResponseState<MoviesRes>>
}
