package com.marslan.chatarneca.fragments.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marslan.chatarneca.data.userdb.EntityUser
import com.marslan.chatarneca.databinding.ItemChatListBinding
import kotlin.collections.ArrayList

class ContactAdapter (
    var currentList: ArrayList<EntityUser>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemChatListBinding.inflate(inflate, parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChatViewHolder).bind(currentList[position], clickListener)
    }

    inner class ChatViewHolder(private val binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: EntityUser, clickListener: (String) -> Unit) {
            binding.chatDate.text = ""
            binding.chatText.text = user.mail
            binding.chatName.text = user.name
            binding.root.setOnClickListener { clickListener(user.id) }
        }
    }

    override fun getItemCount() = currentList.size
}