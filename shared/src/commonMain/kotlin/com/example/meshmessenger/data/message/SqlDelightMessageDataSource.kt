package com.example.meshmessenger.data.message

import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.domain.models.message.Message
import com.example.meshmessenger.domain.models.message.MessageDataSource
import com.squareup.sqldelight.db.SqlDriver

class SqlDelightMessageDataSource(sqlDriver: SqlDriver): MessageDataSource {

    private val db = AppDatabase(sqlDriver)
    private val queries = db.appDatabaseQueries

    override suspend fun getAllMessages(): List<Message> {
        return queries
            .getAllMessageEntity()
            .executeAsList()
            .map { it.toMessage() }
    }


    override suspend fun getMessagesById(id: Long): Message? {
        return queries
            .getMessagesById(id)
            .executeAsOneOrNull()
            ?.toMessage()
    }

    override suspend fun insertMessage(message: Message) {
        queries.insertMessage(
             message_id = message.message_id,
             from_id = message.from_id,
             to_chat_id = message.to_chat_id,
             text_content = message.text_content,
             created_at = message.created_at,
             sent_at = message.sent_at,
             status = message.status,
             has_attachments = message.has_attachment
        )
    }

    override suspend fun deleteMessageById(id: Long) {
        queries.deleteMessagesById(id)
    }

    override suspend fun getAllMessagesFromOneChat(id: Long): List<Message> {
        return queries.getAllMessagesFromOneChat(id).executeAsList().map { it.toMessage() }
    }
    override suspend fun deleteAllMessages() {
        queries.deleteAllMessages()
    }
}