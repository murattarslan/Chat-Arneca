package com.marslan.chatarneca.data

data class Message(
    val text: String = "",
    val fromID: String = "-1",
    val date: String = "00/00/00 00:00",
    val read: Boolean = false,
    val send: Boolean = false,
)