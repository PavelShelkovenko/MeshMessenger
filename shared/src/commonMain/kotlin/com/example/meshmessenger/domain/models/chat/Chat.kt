package com.example.meshmessenger.domain.models.chat

data class Chat(
   val chat_id: Long,
   val chat_name: String,
   val chat_image: String,
   val type: String,
   val created_at: Long,
   val id_peer_created: String?,
)
