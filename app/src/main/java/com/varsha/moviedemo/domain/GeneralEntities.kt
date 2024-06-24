package com.varsha.moviedemo.domain

class InvalidException(message: String) : Exception(message)
class NoConnectivityException(message: String) : Exception(message)

data class ClientErrorResponse(
    val code: Int,
    val message: String,
    val error: Error
)