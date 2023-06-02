package com.example.meshmessenger.data.attachment

import com.example.meshmessenger.domain.attachment.Attachment
import database.AttachmentEntity

fun AttachmentEntity.toAttachments(): Attachment {
    return Attachment(
        id_attachment = id_attachment,
        id_message = id_message,
        content_type = content_type,
        file_path = file_path,
    )
}