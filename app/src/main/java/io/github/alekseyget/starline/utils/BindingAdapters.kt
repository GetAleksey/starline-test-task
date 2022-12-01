package io.github.alekseyget.starline.utils

import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import io.github.alekseyget.starline.R

@BindingAdapter("app:textResource")
fun setTextFromResource(textView: TextView, @StringRes resId: Int) {
    textView.text = textView.context.getString(resId)
}

@BindingAdapter("app:image")
fun loadImageFromFile(imageView: ImageView, path: String?) {
    if (path == null) {
        imageView.setImageResource(R.drawable.placeholder)
        return
    }

    val bitmap = BitmapFactory.decodeFile(path)

    if (bitmap != null) {
        imageView.setImageBitmap(bitmap)
    } else {
        imageView.setImageResource(R.drawable.broken_image)
    }
}