package com.tae.remembersearchapplication.ui

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.api.data.User
import com.tae.remembersearchapplication.databinding.ItemUserBinding
import net.njobler.epoxyhelper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_user)
abstract class UserUIModel : ViewBindingEpoxyModelWithHolder<ItemUserBinding>() {
    @EpoxyAttribute
    lateinit var item: User

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var checkListener: (isChecked: Boolean) -> Unit

    override fun ItemUserBinding.bind() {
        item = this@UserUIModel.item

        constraintMain.setOnClickListener {
            cbFav.isChecked = !cbFav.isChecked
        }

        cbFav.setOnCheckedChangeListener { _, isChecked ->
            checkListener(isChecked)
        }


    }

    override fun ItemUserBinding.unbind() {

    }
}