package com.example.meshmessenger.domain.chat

interface ChatDataSource {
    suspend fun getAllChats(): List<Chat>
    suspend fun getChatById(id: Long): Chat?
    suspend fun insertChat(chat: Chat)
    suspend fun deleteChatById(id: Long)
}