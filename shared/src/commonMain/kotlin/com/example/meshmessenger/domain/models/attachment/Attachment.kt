package com.example.meshmessenger.domain.models.attachment

data class Attachment(
    val id_attachment: Long,
    val id_message: Long,
    val content_type: String,
    val file_path: String,
)
