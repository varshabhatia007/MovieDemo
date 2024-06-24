package com.varsha.moviedemo.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun <T> performNetworkFlow(networkCall: suspend () -> T): Flow<T> =
    flow {
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }.flowOn(Dispatchers.IO)