package com.example.meshmessenger.domain.message

interface MessageDataSource {
    suspend fun getAllMessages(): List<Message>
    suspend fun getMessagesById(id: Long): Message?
    suspend fun insertMessage(message: Message)
    suspend fun deleteMessageById(id: Long)
}