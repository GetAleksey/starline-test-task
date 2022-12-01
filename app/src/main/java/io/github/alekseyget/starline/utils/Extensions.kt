package io.github.alekseyget.starline.utils

import android.content.Context
import io.github.alekseyget.starline.App
import io.github.alekseyget.starline.feature.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> this.appComponent
        else -> this.applicationContext.appComponent
    }