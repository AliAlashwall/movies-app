package com.example.movies.util

import com.example.movies.domain.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> reformatingApi(
    execute: suspend () -> Response<T>
): Flow<ResponseState<T>> {
    return flow {
        emit(ResponseState.Loading)
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                val errorBody = response.errorBody()?.string()
                val message = parseErrorBody(errorBody) ?: response.message()
                emit(ResponseState.Error(response.code(), message))
            }
        } catch (e: HttpException) {
            emit(ResponseState.Error(e.code(), e.message()))
        } catch (e: Throwable) {
            emit(ResponseState.Error(0, e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)
}

