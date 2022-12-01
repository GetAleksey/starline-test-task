package io.github.alekseyget.starline.feature.main.di

import dagger.Binds
import dagger.Module
import io.github.alekseyget.starline.feature.main.data.ImagesRepository
import io.github.alekseyget.starline.feature.main.data.ImagesRepositoryImpl

@Module
interface BindModule {
    @Binds
    fun bindImagesRepository(repository: ImagesRepositoryImpl): ImagesRepository
}