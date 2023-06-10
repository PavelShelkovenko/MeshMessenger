package com.example.meshmessenger.domain.models.chat_members

import com.example.meshmessenger.domain.models.user.User

interface ChatMembersDataSource {
    suspend fun getAllChatMembers(): List<ChatMembers>
    suspend fun getChatMembersById(id: Long): ChatMembers?
    suspend fun insertChatMembers(chatMembers: ChatMembers)
    suspend fun deleteChatMembersById(id: Long)
    suspend fun fetchAllUsersFromChat(id: Long): List<User>

}