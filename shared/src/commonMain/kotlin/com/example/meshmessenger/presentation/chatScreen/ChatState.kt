package com.example.meshmessenger.presentation.chatScreen

import com.example.meshmessenger.data.Message

data class ChatState(
    val channelName: String = "",
    val channelSurname: String = "",
    var textOfMessage: String = "",

    var messagesList: MutableList<Message> = mutableListOf()
)