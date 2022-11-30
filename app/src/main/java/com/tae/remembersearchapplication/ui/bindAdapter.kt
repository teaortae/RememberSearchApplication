package com.tae.remembersearchapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.tae.remembersearchapplication.UserBindingModel_
import com.tae.remembersearchapplication.UserHeaderBindingModel_
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
        res?.headerLetterList

            ?.map {user->
                //alphabet header
                UserHeaderBindingModel_()
                    .id(user.id)
                    .item(user)
                    .addIf(user.isHeader, this)

                //user item
                UserUIModel_()
                    .id(user.id)
                    .item(user)
                    .checkListener {
                        user.isChecked = it
                    }
                    .addIf(! user.isHeader, this)

            }
    }
}