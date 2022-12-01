package io.github.alekseyget.starline.feature.main.data

import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException
import javax.inject.Inject

class FileDownloader @Inject constructor(private val client: OkHttpClient) {

    fun download(url: String, file: File) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IOException()
            }

            val body = response.body ?: throw IOException()

            file.sink().buffer().use { sink ->
                sink.writeAll(body.source())
            }
        }
    }

}