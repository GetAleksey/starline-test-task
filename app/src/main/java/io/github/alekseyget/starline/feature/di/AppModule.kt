package io.github.alekseyget.starline.feature.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.alekseyget.starline.feature.main.di.BindModule
import io.github.alekseyget.starline.feature.main.di.MainModule
import javax.inject.Singleton

@Module(includes = [MainModule::class, BindModule::class])
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = application

}