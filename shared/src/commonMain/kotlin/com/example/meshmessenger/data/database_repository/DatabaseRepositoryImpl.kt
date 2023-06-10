package com.example.meshmessenger.data.database_repository

import com.example.meshmessenger.domain.models.attachment.Attachment
import com.example.meshmessenger.domain.models.attachment.AttachmentDataSource
import com.example.meshmessenger.domain.models.chat.Chat
import com.example.meshmessenger.domain.models.chat.ChatDataSource
import com.example.meshmessenger.domain.models.chat_members.ChatMembers
import com.example.meshmessenger.domain.models.chat_members.ChatMembersDataSource
import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import com.example.meshmessenger.domain.models.message.Message
import com.example.meshmessenger.domain.models.message.MessageDataSource
import com.example.meshmessenger.domain.models.user.User
import com.example.meshmessenger.domain.models.user.UserDataSource

class DatabaseRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val messageDataSource: MessageDataSource,
    private val chatMembersDataSource: ChatMembersDataSource,
    private val chatDataSource: ChatDataSource,
    private val attachmentDataSource: AttachmentDataSource
): DatabaseRepository {
    override suspend fun getAllMessages(): List<Message> {
       return messageDataSource.getAllMessages()
    }

    override suspend fun getMessagesById(id: Long): Message? {
        return messageDataSource.getMessagesById(id)
    }

    override suspend fun insertMessage(message: Message) {
        return messageDataSource.insertMessage(message)
    }

    override suspend fun deleteMessageById(id: Long) {
        return messageDataSource.deleteMessageById(id)
    }

    override suspend fun getAllMessagesFromOneChat(id: Long): List<Message> {
        return messageDataSource.getAllMessagesFromOneChat(id)
    }

    override suspend fun deleteAllMessages() {
        return messageDataSource.deleteAllMessages()
    }

    override suspend fun getAllUsers(): List<User> {
        return userDataSource.getAllUsers()
    }

    override suspend fun getUserById(id: String): User? {
        return userDataSource.getUserById(id)
    }

    override suspend fun insertUser(user: User) {
        return userDataSource.insertUser(user)
    }

    override suspend fun deleteUserById(id: String) {
        return userDataSource.deleteUserById(id)
    }

    override suspend fun getAllChats(): List<Chat> {
        return chatDataSource.getAllChats()
    }

    override suspend fun getChatById(id: Long): Chat? {
        return chatDataSource.getChatById(id)
    }

    override suspend fun insertChat(chat: Chat) {
        return chatDataSource.insertChat(chat)
    }

    override suspend fun deleteChatById(id: Long) {
        return chatDataSource.deleteChatById(id)
    }

    override suspend fun getAllChatMembers(): List<ChatMembers> {
        return chatMembersDataSource.getAllChatMembers()
    }

    override suspend fun getChatMembersById(id: Long): ChatMembers? {
        return chatMembersDataSource.getChatMembersById(id)
    }

    override suspend fun insertChatMembers(chatMembers: ChatMembers) {
        return chatMembersDataSource.insertChatMembers(chatMembers)
    }

    override suspend fun deleteChatMembersById(id: Long) {
        return chatMembersDataSource.deleteChatMembersById(id)
    }

    override suspend fun fetchAllUsersFromChat(id: Long): List<User> {
        return chatMembersDataSource.fetchAllUsersFromChat(id)
    }

    override suspend fun getAllAttachments(): List<Attachment> {
        return attachmentDataSource.getAllAttachments()
    }

    override suspend fun getAttachmentsById(id: Long): Attachment? {
        return attachmentDataSource.getAttachmentsById(id)
    }

    override suspend fun insertAttachments(attachment: Attachment) {
        return attachmentDataSource.insertAttachments(attachment)
    }

    override suspend fun deleteAttachmentsById(id: Long) {
        return attachmentDataSource.deleteAttachmentsById(id)
    }
}