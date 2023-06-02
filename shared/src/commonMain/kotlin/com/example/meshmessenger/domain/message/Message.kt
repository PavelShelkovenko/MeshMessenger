package com.example.meshmessenger.domain.message

data class Message(
    val message_id: Long,
    val from_id: String,
    val to_chat_id: Long,
    val text_content: String?,
    val created_at: String,
    val sent_at: String,
    val status: String,
    val has_attachment: Boolean,
)
