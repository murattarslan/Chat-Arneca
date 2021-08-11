package com.marslan.chatarneca.data.chatdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatDB")
data class EntityChat(
    @PrimaryKey(autoGenerate = true)
    val chatID: Int = -1,
    val chatName: String = "chat",
    val toID: String = "null",
    val lastMessage: String = "hi!",
    val lastDate: String = "00:00"
)