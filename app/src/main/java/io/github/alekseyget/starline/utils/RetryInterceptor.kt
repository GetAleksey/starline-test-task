package io.github.alekseyget.starline.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

private const val ATTEMPTS_COUNT = 3

class RetryInterceptor(private val attemptsCount: Int = ATTEMPTS_COUNT): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)

        var attemptsLeft = attemptsCount
        while (!response.isSuccessful && attemptsLeft > 0) {
            val attemptNumber = attemptsCount - attemptsLeft + 1
            Log.d("Interceptor", "Request failed: $attemptNumber")

            attemptsLeft--

            response.close()
            response = chain.proceed(request)
        }

        return response
    }

}