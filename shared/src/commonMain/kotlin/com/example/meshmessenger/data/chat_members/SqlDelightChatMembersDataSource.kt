package com.example.meshmessenger.data.chat_members

import com.example.meshmessenger.data.user.toUser
import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.domain.chat_members.ChatMembers
import com.example.meshmessenger.domain.chat_members.ChatMembersDataSource
import com.example.meshmessenger.domain.user.User

class SqlDelightChatMembersDataSource(db: AppDatabase): ChatMembersDataSource {

    private val queries = db.appDatabaseQueries

    override suspend fun getAllChatMembers(): List<ChatMembers> {
        return queries
            .getAllChatsMemebers()
            .executeAsList()
            .map { it.toChatMembers() }
    }


    override suspend fun getChatMembersById(id: Long): ChatMembers? {
        return queries
            .getChatsMembersById(id)
            .executeAsOneOrNull()
            ?.toChatMembers()
    }

    override suspend fun insertChatMembers(chatMembers: ChatMembers) {
        queries.insertChatsMembers(
            id_chat_members = chatMembers.id_chat_members,
            id_peer = chatMembers.id_peer,
            chat_id = chatMembers.chat_id,
            time_add_at = chatMembers.time_add_at,
            is_admin = chatMembers.is_admin
        )
    }

    override suspend fun deleteChatMembersById(id: Long) {
        queries.deleteChatsMembersById(id)
    }

    override suspend fun fetchAllUsersFromChat(id: Long): List<User> {
       return queries
           .fetchAllUsersFromChat(id)
           .executeAsList()
           .map { it.toUser() }
    }
}