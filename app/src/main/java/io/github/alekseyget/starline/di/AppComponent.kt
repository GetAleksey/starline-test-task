package io.github.alekseyget.starline.di

import dagger.Component
import io.github.alekseyget.starline.feature.main.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

}