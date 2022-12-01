package io.github.alekseyget.starline

import android.app.Application
import io.github.alekseyget.starline.di.AppComponent
import io.github.alekseyget.starline.di.AppModule
import io.github.alekseyget.starline.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}