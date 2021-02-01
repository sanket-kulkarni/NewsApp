package com.example.newsapp.adapter


import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.R
import com.example.newsapp.databinding.ListItemBinding
import com.example.newsapp.model.Article

class NewsViewHolder (private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onItemClicked: (Article, ImageView) -> Unit) {
        binding.postTitle.text = article.title
        binding.postAuthor.text = article.author
        binding.imageView.load(article.urlToImage) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(article, binding.imageView)
        }
    }
}