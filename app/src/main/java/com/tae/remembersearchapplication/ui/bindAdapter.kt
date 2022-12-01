package com.tae.remembersearchapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

@BindingAdapter(value = ["userImg"])
fun userImg(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .transform(CircleCrop())
        .into(imageView)

}

@BindingAdapter(value = ["adapter", "data"])
fun <T> addAdapter(
    recyclerView: RecyclerView,
    listAdapter: ListAdapter<T, RecyclerView.ViewHolder>?,
    list: List<T>?,
) {
    recyclerView.apply {
        adapter = listAdapter
    }
    listAdapter?.submitList(list)
}
