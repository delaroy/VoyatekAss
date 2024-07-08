package com.mobile.voyatekcoding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.domain.model.UserResponseData
import com.mobile.voyatekcoding.databinding.UserItemBinding

class UserListAdapter(private val userDetails: UserDetailClick) : ListAdapter<UserResponseData, ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item.let {
            holder.apply {
                bind(item)
                itemView.tag = item
            }
        }

        holder.itemView.setOnClickListener {
            userDetails.clickOnItem(
                item
            )
        }

    }
}

class ViewHolder(private val binding: UserItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UserResponseData) {
        binding.apply {
            model = item
            executePendingBindings()
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<UserResponseData>() {

    override fun areItemsTheSame(oldItem: UserResponseData, newItem: UserResponseData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: UserResponseData,
        newItem: UserResponseData
    ): Boolean =
        oldItem == newItem
}

interface UserDetailClick {
    fun clickOnItem(data: UserResponseData)
}