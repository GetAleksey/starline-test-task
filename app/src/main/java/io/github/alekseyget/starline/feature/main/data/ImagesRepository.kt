package io.github.alekseyget.starline.feature.main.data

import io.reactivex.rxjava3.core.Single

interface ImagesRepository {
    fun getImage(url: String): Single<String>
}