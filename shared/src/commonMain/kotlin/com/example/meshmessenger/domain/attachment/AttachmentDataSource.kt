package com.example.meshmessenger.domain.attachment

interface AttachmentDataSource {
    suspend fun getAllAttachments(): List<Attachment>
    suspend fun getAttachmentsById(id: Long): Attachment?
    suspend fun insertAttachments(attachment: Attachment)
    suspend fun deleteAttachmentsById(id: Long)
}