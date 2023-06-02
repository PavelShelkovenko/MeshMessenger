package com.example.meshmessenger.domain.chat_members

interface ChatMembersDataSource {
    suspend fun getAllChatMembers(): List<ChatMembers>
    suspend fun getChatMembersById(id: Long): ChatMembers?
    suspend fun insertChatMembers(chatMembers: ChatMembers)
    suspend fun deleteChatMembersById(id: Long)
}