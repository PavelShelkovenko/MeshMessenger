package com.example.meshmessenger.data.chats

import com.example.meshmessenger.domain.chat.Chat
import database.ChatEntity


fun ChatEntity.toChat(): Chat {
    return Chat(
        chat_id = chat_id,
        chat_name = chat_name,
        type = type,
        created_at = created_at,
        id_peer_created = id_peer_created!!,
    )
}