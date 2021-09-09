package com.example.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userlist.databinding.ItemUserBinding
import com.example.userlist.model.User


class UserListAdapter(private val itemclickCallback: (String) -> Unit) :
    ListAdapter<User, UserListAdapter.ViewHolder>(DiffCallBack())  {



    class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: User,itemclickCallback :(String) -> Unit ) {
            binding.apply {
                binding.user = item
                binding.constrainsLayoutParent.setOnClickListener {
                    itemclickCallback.invoke(item.login)
                }
                executePendingBindings()
            }
        }
    }
    class DiffCallBack : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = getItem(position)
        holder.bind(item, itemclickCallback)
    }

}