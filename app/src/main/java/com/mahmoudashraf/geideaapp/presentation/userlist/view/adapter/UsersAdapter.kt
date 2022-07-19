package com.mahmoudashraf.geideaapp.presentation.userlist.view.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudashraf.geideaapp.core.view.viewBinding
import com.mahmoudashraf.geideaapp.data.entity.User
import com.mahmoudashraf.geideaapp.databinding.ItemUsersListBinding


class UsersAdapter(
    private val data: List<User> = listOf(),
    private val onItemClicked: (User) -> Unit
) :
    RecyclerView.Adapter<UsersAdapter.Holder>() {

    class Holder(private val binding: ItemUsersListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User, onItemClicked: (User) -> Unit) {
            with(binding) {
                container.setOnClickListener{onItemClicked(user)}
                tvName.text = "${user.firstName} ${user.lastName}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.viewBinding(ItemUsersListBinding::inflate))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], onItemClicked)
    }

    override fun getItemCount() = data.size



}