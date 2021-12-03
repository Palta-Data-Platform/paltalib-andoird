package com.paltabrain.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val HTTP_OK = 200

class NetworkClient {

    private val client = OkHttpClient()

    suspend fun performRequest(request: Request, callback: (NetworkResult<String>) -> Unit) {
        try {
            val response = call(request)
            if (response.code == HTTP_OK) {
                response.body?.let {
                    callback(NetworkResult.Success(it.string()))
                } ?: callback(NetworkResult.Error("Server error"))
            }
        } catch (e: IOException) {
            callback(NetworkResult.Error(e.message))
        }
    }

    private suspend fun call(request: Request): Response = withContext(Dispatchers.IO) {
        val result = async { client.newCall(request).execute() }
        result.await()
    }
}
