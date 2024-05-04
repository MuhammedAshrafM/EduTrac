package com.invade.core.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoConnectivityException()
        }
        val originalRequest = chain.request()
        val builder: Request.Builder = originalRequest
            .newBuilder()
            .addHeader("Content-Type","application/json")
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager   =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo?.isConnected ?: false
    }
}
