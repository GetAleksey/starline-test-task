package io.github.alekseyget.starline.feature.main.data

import io.reactivex.rxjava3.core.Observable

interface ImagesRepository {
    fun getImage(url: String): Observable<String>
}