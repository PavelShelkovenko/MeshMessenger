package com.example.meshmessenger.domain.database_repository

import com.example.meshmessenger.domain.attachment.AttachmentDataSource
import com.example.meshmessenger.domain.chat.ChatDataSource
import com.example.meshmessenger.domain.chat_members.ChatMembersDataSource
import com.example.meshmessenger.domain.message.MessageDataSource
import com.example.meshmessenger.domain.user.UserDataSource

interface DatabaseRepository: MessageDataSource, UserDataSource, ChatDataSource, ChatMembersDataSource, AttachmentDataSource