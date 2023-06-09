package com.example.meshmessenger.data.chats

import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.domain.chat.Chat
import com.example.meshmessenger.domain.chat.ChatDataSource
import com.squareup.sqldelight.db.SqlDriver

class SqlDelightChatDataSource(sqlDriver: SqlDriver): ChatDataSource {

    private val db = AppDatabase(sqlDriver)
    private val queries = db.appDatabaseQueries

    override suspend fun getAllChats(): List<Chat> {
        return queries
            .getAllChats()
            .executeAsList()
            .map { it.toChat() }
    }


    override suspend fun getChatById(id: Long): Chat? {
        return queries
            .getChatById(id)
            .executeAsOneOrNull()
            ?.toChat()
    }

    override suspend fun insertChat(chat: Chat) {
        queries.insertChat(
            chat_id = chat.chat_id,
            chat_name = chat.chat_name,
            created_at = chat.created_at,
            id_peer_created = chat.id_peer_created,
            type = chat.type
        )
    }

    override suspend fun deleteChatById(id: Long) {
        queries.deleteChatById(id)
    }
}