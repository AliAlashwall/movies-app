package com.example.movies.data.repository


import com.example.movies.data.remote.RetrofitService
import com.example.movies.domain.ResponseState
import com.example.movies.domain.model.GenresRes
import com.example.movies.domain.model.MovieDetailsRes
import com.example.movies.domain.model.MoviesRes
import com.example.movies.domain.repository.MovieRepository
import com.example.movies.util.reformatingApi
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val retrofitService: RetrofitService
) : MovieRepository {
    override suspend fun getGenres(language: String): Flow<ResponseState<GenresRes>> =
        reformatingApi { retrofitService.getGenres(language) }


    override suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): Flow<ResponseState<MovieDetailsRes>> =
        reformatingApi { retrofitService.getMovieDetails (movieId, language) }

    override suspend fun discoverMovies(
        includeAdult: Boolean,
        includeVideo: Boolean,
        language: String,
        page: Int,
        sortBy: String
    ): Flow<ResponseState<MoviesRes>> =
        reformatingApi { retrofitService.discoverMovies(includeAdult, includeVideo, language, page, sortBy) }

    override suspend fun searchMovies(
        query: String,
        includeAdult: Boolean,
        language: String,
        page: Int
    ): Flow<ResponseState<MoviesRes>> =
        reformatingApi { retrofitService.searchMovies(query, includeAdult, language, page) }


}