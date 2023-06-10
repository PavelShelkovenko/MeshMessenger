package com.example.meshmessenger.data.message

import com.example.meshmessenger.domain.models.message.Message
import database.MessageEntity


fun MessageEntity.toMessage(): Message {
    return Message(
        message_id = message_id,
        from_id = from_id,
        to_chat_id = to_chat_id,
        text_content = text_content!!,
        created_at = created_at,
        sent_at = sent_at,
        status = status,
        has_attachment = has_attachments ?: false
    )
}
