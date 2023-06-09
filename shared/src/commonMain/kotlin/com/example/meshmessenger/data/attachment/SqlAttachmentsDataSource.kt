package com.example.meshmessenger.data.attachment

import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.domain.attachment.Attachment
import com.example.meshmessenger.domain.attachment.AttachmentDataSource
import com.squareup.sqldelight.db.SqlDriver

class SqlDelightAttachmentsDataSource(sqlDriver: SqlDriver): AttachmentDataSource {

    private val db = AppDatabase(sqlDriver)
    private val queries = db.appDatabaseQueries

    override suspend fun getAllAttachments(): List<Attachment> {
        return queries
            .getAllattachmentsEntity()
            .executeAsList()
            .map { it.toAttachments() }
    }

    override suspend fun getAttachmentsById(id: Long): Attachment? {
        return queries
            .getAttachmentById(id)
            .executeAsOneOrNull()
            ?.toAttachments()
    }

    override suspend fun insertAttachments(attachment: Attachment) {
        queries.insertAttachment(
            id_attachment = attachment.id_attachment,
            id_message = attachment.id_message,
            content_type = attachment.content_type,
            file_path = attachment.file_path,
        )
    }

    override suspend fun deleteAttachmentsById(id: Long) {
        queries.deleteAttachmentsById(id)
    }
}