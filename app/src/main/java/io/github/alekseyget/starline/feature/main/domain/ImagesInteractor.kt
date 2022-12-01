package io.github.alekseyget.starline.feature.main.domain

import io.github.alekseyget.starline.feature.main.data.ImagesRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ImagesInteractor @Inject constructor(private val repository: ImagesRepository) {

    fun getImage(url: String): Observable<String> = repository.getImage(url)

}