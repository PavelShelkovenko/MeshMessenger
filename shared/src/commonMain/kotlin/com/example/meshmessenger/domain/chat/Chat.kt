package com.example.meshmessenger.domain.chat

data class Chat(
   val chat_id: Long,
   val chat_name: String,
   val type: String,
   val created_at: Long,
   val id_peer_created: String?,
)
