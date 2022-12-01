package io.github.alekseyget.starline.feature.main.data

import io.reactivex.rxjava3.core.Observable
import java.io.File
import java.io.IOException
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Named

class ImagesRepositoryImpl @Inject constructor(
    private val downloader: FileDownloader,
    @param:Named("cacheDir") private val cacheDirectory: File
) : ImagesRepository {

    override fun getImage(url: String): Observable<String> {
        return Observable.create { emitter ->
            val filename = getFilename(url)
            val file = File(cacheDirectory, filename)

            if (file.exists()) {
                emitter.onNext(file.absolutePath)
                return@create
            }

            try {
                downloader.download(url, file)
            } catch (ex: IOException) {
                emitter.onError(ex)
            }

            emitter.onNext(file.absolutePath)
        }

    }

    private fun getFilename(url: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        md5.update(url.toByteArray())
        val digest = md5.digest()
        val filenameBuffer = StringBuffer()

        digest.forEach { byte ->
            val hex = Integer.toHexString(byte.toInt() and 0xFF).padStart(2, '0')
            filenameBuffer.append(hex)
        }

        val fileExtension = url.substringAfterLast('.')
        filenameBuffer.append('.')
        filenameBuffer.append(fileExtension)

        return filenameBuffer.toString()
    }

}