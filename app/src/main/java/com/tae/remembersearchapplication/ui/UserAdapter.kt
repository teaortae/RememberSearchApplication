package com.tae.remembersearchapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tae.remembersearchapplication.api.data.User
import com.tae.remembersearchapplication.databinding.ItemUserBinding
import com.tae.remembersearchapplication.databinding.ItemUserHeaderBinding

class UserAdapter : ListAdapter<User, RecyclerView.ViewHolder>(UserDiffUtil()) {

    companion object {
        const val TYPE_ITEM = 1
        const val TYPE_HEADER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> ItemViewHolder(
                ItemUserBinding.inflate(
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

    private fun onBindItemViewHolder(holder: ItemViewHolder, item: User) {
        holder.bind(item)
    }

    private fun onBindHeaderItemViewHolder(holder: HeaderItemViewHolder, item: User) {
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                item = user
                constraintMain.setOnClickListener { cbFav.isChecked = ! cbFav.isChecked }
            }
        }
    }

    inner class HeaderItemViewHolder(private val binding: ItemUserHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.item = user
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].isHeader) TYPE_HEADER else TYPE_ITEM
    }
}

class UserDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}