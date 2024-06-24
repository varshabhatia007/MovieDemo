package com.varsha.moviedemo.network

import com.google.gson.Gson
import com.varsha.moviedemo.domain.ClientErrorResponse
import com.varsha.moviedemo.domain.InvalidException
import retrofit2.Response
import timber.log.Timber

abstract class NetworkResult {
    protected suspend fun <T> getResult(
        call: suspend () -> Response<T>,
        forceError: Boolean = false
    ): T {
        try {
            if (forceError) {
                throw Exception("Exception: ")
            }
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return body
            }

            if (response.code() in 400..499) {
                val errorResponse = Gson().fromJson(
                    response.errorBody()?.string() ?: "",
                    ClientErrorResponse::class.java
                )
                Timber.e("Exception ${errorResponse.error.message}")
                throw InvalidException(errorResponse.error.message ?: "Error")
            }
            throw Exception("Other Exception: ${response.code()} ${response.body()}")
        } catch (e: Throwable) {
            error(e.message ?: "")
        }
    }
}