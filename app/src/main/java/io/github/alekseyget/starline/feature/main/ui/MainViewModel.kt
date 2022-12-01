package io.github.alekseyget.starline.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.alekseyget.starline.R
import io.github.alekseyget.starline.feature.main.domain.ImagesInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel(private val imagesInteractor: ImagesInteractor) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val testData: List<Post> = listOf(
        Post.Image(id = 0, url = "https://www.starline.ru/wp-content/uploads/2019/02/blog_tele.jpg", path = null),
        Post.Image(id = 1, url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/242px-Android_logo_2019_%28stacked%29.svg.png", path = null),
        Post.Text(id = 2, resId = R.string.app_name)
    )

    private val _posts = MutableLiveData<List<Post>>(testData)

    val posts: LiveData<List<Post>> = _posts

    fun loadImage(url: String) {
        val disposable = imagesInteractor.getImage(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { path -> updateImagesWithPath(url, path) },
                { error -> replaceUnavailableImages(url, error.localizedMessage ?: "Unknown error") }
            )

        compositeDisposable.add(disposable)
    }

    private fun updateImagesWithPath(url: String, path: String) =
        replaceImages(url) { image -> image.copy(path = path) }

    private fun replaceUnavailableImages(url: String, errorMessage: String) =
        replaceImages(url) { image ->
            Post.Error(id = image.id, message = errorMessage)
        }

    private fun replaceImages(url: String, makeReplacement: (Post.Image) -> Post) {
        _posts.value?.let { currentPosts ->
            val updatedPosts = currentPosts.map { post ->
                if (post is Post.Image && post.url == url) {
                    makeReplacement(post)
                } else {
                    post
                }
            }

            _posts.setValue(updatedPosts)
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val imagesInteractor: ImagesInteractor
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainViewModel::class.java)
            return MainViewModel(imagesInteractor) as T
        }
    }

}