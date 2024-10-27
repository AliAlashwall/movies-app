package com.example.movies.domain

sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val code: Int, val message: String) : ResponseState<Nothing>()
    data object Loading : ResponseState<Nothing>()
    data object Empty : ResponseState<Nothing>()
}