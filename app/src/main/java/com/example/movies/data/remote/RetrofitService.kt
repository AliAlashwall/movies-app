package com.example.movies.data.remote

import com.example.movies.domain.model.GenresRes
import com.example.movies.domain.model.MovieDetailsRes
import com.example.movies.domain.model.MoviesRes
import com.example.movies.util.Constants.GENRES_ENDPOINT
import com.example.movies.util.Constants.MOVIE_DETAILS_ENDPOINT
import com.example.movies.util.Constants.POPULAR_ENDPOINT
import com.example.movies.util.Constants.SEARCH_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {

    @GET(MOVIE_DETAILS_ENDPOINT)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetailsRes>

    @GET(SEARCH_ENDPOINT)
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesRes>

    @GET(POPULAR_ENDPOINT)
    suspend fun discoverMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<MoviesRes>

    @GET(GENRES_ENDPOINT)
    suspend fun getGenres(@Query("language") language: String = "en"): Response<GenresRes>
}