package com.example.meshmessenger.domain.models.chat_members

data class ChatMembers(
     val id_chat_members: Long,
     val id_peer: String,
     val chat_id: Long,
     val time_add_at: Long,
     val is_admin: Boolean
)
