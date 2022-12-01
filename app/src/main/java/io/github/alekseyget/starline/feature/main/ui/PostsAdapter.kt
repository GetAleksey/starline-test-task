package io.github.alekseyget.starline.feature.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.alekseyget.starline.R
import io.github.alekseyget.starline.databinding.ItemErrorBinding
import io.github.alekseyget.starline.databinding.ItemImageBinding
import io.github.alekseyget.starline.databinding.ItemTextBinding

private const val VIEW_TYPE_IMAGE = 0
private const val VIEW_TYPE_ERROR = 1
private const val VIEW_TYPE_TEXT = 2

class PostsAdapter(
    private val onImageBind: (String) -> Unit
) : ListAdapter<Post, RecyclerView.ViewHolder>(PostDiffCallback) {

    override fun getItemViewType(position: Int) =
        when (getItem(position)) {
            is Post.Image -> VIEW_TYPE_IMAGE
            is Post.Error -> VIEW_TYPE_ERROR
            is Post.Text -> VIEW_TYPE_TEXT
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_IMAGE -> {
                val binding = DataBindingUtil.inflate<ItemImageBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_image,
                    parent,
                    false
                )

                ImageViewHolder(binding, onImageBind)
            }
            VIEW_TYPE_ERROR -> {
                val binding = DataBindingUtil.inflate<ItemErrorBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_error,
                    parent,
                    false
                )

                ErrorViewHolder(binding)
            }
            VIEW_TYPE_TEXT -> {
                val binding = DataBindingUtil.inflate<ItemTextBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_text,
                    parent,
                    false
                )

                TextViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is Post.Image -> (holder as ImageViewHolder).bind(item)
            is Post.Error -> (holder as ErrorViewHolder).bind(item)
            is Post.Text -> (holder as TextViewHolder).bind(item)
        }
    }

}

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        when {
            oldItem is Post.Image && newItem is Post.Image -> oldItem.id == newItem.id
            oldItem is Post.Error && newItem is Post.Error -> oldItem.id == newItem.id
            oldItem is Post.Text && newItem is Post.Text -> oldItem.id == newItem.id
            else -> false
        }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        when {
            oldItem is Post.Image && newItem is Post.Image -> oldItem == newItem
            oldItem is Post.Error && newItem is Post.Error -> oldItem == newItem
            oldItem is Post.Text && newItem is Post.Text -> oldItem == newItem
            else -> false
        }
}

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val onImageBind: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Post.Image) {
        binding.image = image
        binding.executePendingBindings()

        if (image.path == null) {
            onImageBind(image.url)
        }
    }
}

class ErrorViewHolder(
    private val binding: ItemErrorBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(error: Post.Error) {
        binding.error = error
        binding.executePendingBindings()
    }
}

class TextViewHolder(private val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: Post.Text) {
        binding.text = text
        binding.executePendingBindings()
    }
}