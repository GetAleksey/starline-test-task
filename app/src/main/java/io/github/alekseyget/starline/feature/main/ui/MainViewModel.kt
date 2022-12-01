package io.github.alekseyget.starline.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.alekseyget.starline.R

class MainViewModel() : ViewModel() {

    private val testData: List<Post> = listOf(
        Post.Image(id = 0, url = "https://www.starline.ru/wp-content/uploads/2019/02/blog_tele.jpg", path = null),
        Post.Image(id = 0, url = "https://www.starline.ru/wp-content/uploads/2019/02/blog_tele1.jpg", path = null),
        Post.Text(id = 2, resId = R.string.app_name)
    )

    private val _posts = MutableLiveData<List<Post>>(testData)

    val posts: LiveData<List<Post>> = _posts

    fun loadImage(url: String) {
        TODO()
    }

}