package com.tae.remembersearchapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.tae.remembersearchapplication.api.data.UserRes
import com.tae.remembersearchapplication.user

@BindingAdapter(value = ["userImg"])
fun userImg(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .transform(CircleCrop())
        .into(imageView)

}

@BindingAdapter(value = ["setRecycler"])
fun setRecycler(recyclerView: EpoxyRecyclerView, res: UserRes?) {
    recyclerView.withModels {
        res?.items
            ?.sortedBy { it.login.lowercase() }//소문자 변경후 소팅
            ?.map {
                user {
                    id(it.id)
                    item(it)
                }
            }
    }
}