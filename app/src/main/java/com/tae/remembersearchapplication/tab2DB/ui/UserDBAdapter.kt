package com.tae.remembersearchapplication.tab2DB.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tae.remembersearchapplication.databinding.ItemUserDbBinding
import com.tae.remembersearchapplication.databinding.ItemUserHeaderBinding
import com.tae.remembersearchapplication.tab1Api.api.data.User
import com.tae.remembersearchapplication.tab1Api.ui.OnCheckChangeListener
import com.tae.remembersearchapplication.tab2DB.db.UserEntity

class UserDBAdapter : ListAdapter<UserEntity, RecyclerView.ViewHolder>(UserDBDiffUtil()) {

    companion object {
        const val TYPE_ITEM = 1
        const val TYPE_HEADER = 2
    }

    var onCheckChangeListener: OnCheckChangeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> ItemViewHolder(
                ItemUserDbBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> HeaderItemViewHolder(
                ItemUserHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> onBindItemViewHolder(holder, getItem(position))
            is HeaderItemViewHolder -> onBindHeaderItemViewHolder(holder, getItem(position))
        }
    }

    private fun onBindItemViewHolder(holder: ItemViewHolder, item: UserEntity) {
        holder.bind(item)
    }

    private fun onBindHeaderItemViewHolder(holder: HeaderItemViewHolder, item: UserEntity) {
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemUserDbBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.apply {
                item = user
                root.setOnClickListener {
                    user.isChecked = ! user.isChecked
                    notifyItemChanged(adapterPosition)
                    onCheckChangeListener?.onCheckChange(
                        User(
                            login = user.login,
                            id = user.id,
                            avatar_url = user.avatar_url,
                            header = user.header,
                            isHeader = user.isHeader,
                            isChecked = user.isChecked
                        )
                    )
                }
            }
        }
    }

    inner class HeaderItemViewHolder(private val binding: ItemUserHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.header = user.header
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].isHeader) TYPE_HEADER else TYPE_ITEM
    }
}

class UserDBDiffUtil : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }

}