package com.example.meshmessenger.data.chat_members

import com.example.meshmessenger.domain.chat_members.ChatMembers
import database.ChatMemberEntity

fun ChatMemberEntity.toChatMembers(): ChatMembers {
    return ChatMembers(
        id_chat_members = id_chat_members,
        id_peer = id_peer,
        chat_id = chat_id,
        time_add_at = time_add_at,
        is_admin = is_admin,
    )
}