package com.paltalib.network

import com.amplitude.util.DoubleCheck
import com.amplitude.util.Provider
import com.google.gson.Gson
import com.paltalib.Constants
import com.paltalib.entity.Target
import com.paltalib.entity.TargetList
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkClient {

    private var callFactory: Call.Factory

    init {
        val callProvider: Provider<Call.Factory> = DoubleCheck.provider(
            Provider { OkHttpClient() })

        callFactory = Call.Factory { request: Request ->
            callProvider.get().newCall(request)
        }
    }

    fun getTargets(apiKey: String): List<Target> {
        val request = Request.Builder().apply {
            url(Constants.PALTA_CONFIG_URL.plus(apiKey))
            header(Constants.API_KEY_HEADER, apiKey)
        }.build()

        val response =  callFactory.newCall(request).execute()

        return response.body?.let {
             Gson().fromJson(it.string(), TargetList::class.java).targets
        } ?: emptyList()
    }

    suspend fun getTargetsAsync(apiKey: String): List<Target> = withContext(Dispatchers.IO) {
        val targets = async { getTargets(apiKey) }
        targets.await()
    }
}
