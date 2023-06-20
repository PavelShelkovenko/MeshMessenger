package com.example.meshmessenger.data.chats

import com.example.meshmessenger.domain.models.chat.Chat
import database.ChatEntity


fun ChatEntity.toChat() = Chat(
        chat_id = chat_id,
        chat_name = chat_name,
        chat_image = chat_image,
        type = type,
        created_at = created_at,
        id_peer_created = id_peer_created!!,
)