package com.example.meshmessenger.presentation.chatScreen

import com.example.meshmessenger.data.Message

sealed class ChatEvent {
    data class TextChanged(val textChanged: String) : ChatEvent()
    data class MessageSend(val message: Message) : ChatEvent()
}