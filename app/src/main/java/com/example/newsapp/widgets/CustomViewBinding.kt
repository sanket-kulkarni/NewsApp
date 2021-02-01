package com.example.newsapp.widgets

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.LoadingState
import com.example.newsapp.R

class CustomViewBinding {
    companion object {
        @BindingAdapter(value = ["setAdapter"])
        @JvmStatic
        fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
            this.run {
                this.setHasFixedSize(true)
                this.adapter = adapter
            }

        }

        @BindingAdapter(value = ["setupVisibility"])
        @JvmStatic
        fun ProgressBar.progressVisibility(loadingState: LoadingState?) {
            loadingState?.let {
                isVisible = when (it.status) {
                    LoadingState.Status.RUNNING -> true
                    LoadingState.Status.SUCCESS -> false
                    LoadingState.Status.FAILED -> false
                }
            }
        }

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String?) {
           // if (!url.isNullOrEmpty())

                view.load(url) {
                    placeholder(R.drawable.ic_photo)
                    error(R.drawable.ic_broken_image)
                }

        }
    }
}