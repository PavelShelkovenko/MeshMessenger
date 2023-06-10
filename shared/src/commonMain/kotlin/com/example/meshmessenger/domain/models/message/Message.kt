package com.example.meshmessenger.domain.models.message

data class Message(
    val message_id: Long,
    val from_id: String,
    val to_chat_id: Long,
    val text_content: String,
    val created_at: Long,
    val sent_at: Long,
    val status: String,
    val has_attachment: Boolean?,
)
