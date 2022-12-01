package io.github.alekseyget.starline.feature.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.alekseyget.starline.R
import io.github.alekseyget.starline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter = PostsAdapter { id ->
        viewModel.loadImage(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = adapter

        viewModel.posts.observe(this, ::updatePosts)
    }

    private fun updatePosts(posts: List<Post>) {
        adapter.submitList(posts)
    }

}