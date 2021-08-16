package com.marslan.chatarneca.fragments.main

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marslan.chatarneca.data.chatdb.EntityChat
import com.marslan.chatarneca.databinding.ItemChatListBinding
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter(private val clickListener: (EntityChat) -> Unit ,private val longClickListener: (Int) -> Boolean) :
    ListAdapter<EntityChat, RecyclerView.ViewHolder>(ItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemChatListBinding.inflate(inflate, parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChatViewHolder).bind(position, clickListener, longClickListener)
    }

    inner class ChatViewHolder(private val binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(position: Int, clickListener: (EntityChat) -> Unit, longClickListener: (Int) -> Boolean) {
            val chat = currentList[position]
            val sdf = SimpleDateFormat("dd/MM/yy HH:mm")
            val date = sdf.format(Date())
            val currentDate = date.split(" ")
            val messageDate = chat.lastDate.split(" ")
            binding.chatDate.text =
                if(messageDate[0] == currentDate[0])
                    messageDate[1]
                else
                    messageDate[0]
            binding.chatText.text = chat.lastMessage
            if(chat.isRead)
                binding.chatText.setTypeface(null,Typeface.BOLD)
            else
                binding.chatText.setTypeface(null,Typeface.NORMAL)
            binding.chatName.text = chat.chatName
            binding.root.setOnClickListener { clickListener(chat) }
            binding.root.setOnLongClickListener { longClickListener(position) }
        }
    }
    private class ItemCallBack: DiffUtil.ItemCallback<EntityChat>() {
        override fun areItemsTheSame(oldItem: EntityChat, newItem: EntityChat) =
            oldItem.lastMessage == newItem.lastMessage
        override fun areContentsTheSame(oldItem: EntityChat, newItem: EntityChat) =
            oldItem == newItem
    }
}