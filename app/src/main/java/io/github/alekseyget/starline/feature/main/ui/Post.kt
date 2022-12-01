package io.github.alekseyget.starline.feature.main.ui

import androidx.annotation.StringRes

sealed class Post {
    abstract val id: Int

    data class Image(override val id: Int, val url: String, val path: String?) : Post()
    data class Error(override val id: Int, val message: String) : Post()
    data class Text(override val id: Int, @StringRes val resId: Int) : Post()
}
