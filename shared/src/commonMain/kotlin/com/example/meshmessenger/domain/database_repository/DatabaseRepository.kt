package com.example.meshmessenger.domain.database_repository

import com.example.meshmessenger.domain.models.attachment.AttachmentDataSource
import com.example.meshmessenger.domain.models.chat.ChatDataSource
import com.example.meshmessenger.domain.models.chat_members.ChatMembersDataSource
import com.example.meshmessenger.domain.models.message.MessageDataSource
import com.example.meshmessenger.domain.models.user.UserDataSource

interface DatabaseRepository: MessageDataSource, UserDataSource, ChatDataSource,
    ChatMembersDataSource, AttachmentDataSource