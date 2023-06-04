package com.example.meshmessenger.android.root

import com.example.meshmessenger.data.attachment.SqlDelightAttachmentsDataSource
import com.example.meshmessenger.data.chat_members.SqlDelightChatMembersDataSource
import com.example.meshmessenger.data.chats.SqlDelightChatDataSource
import com.example.meshmessenger.data.database_repository.DatabaseRepositoryImpl
import com.example.meshmessenger.data.local.DatabaseDriverFactory
import com.example.meshmessenger.data.message.SqlDelightMessageDataSource
import com.example.meshmessenger.data.user.SqlDelightUserDataSource
import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.domain.attachment.AttachmentDataSource
import com.example.meshmessenger.domain.chat.ChatDataSource
import com.example.meshmessenger.domain.chat_members.ChatMembersDataSource
import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import com.example.meshmessenger.domain.message.MessageDataSource
import com.example.meshmessenger.domain.user.UserDataSource
import com.example.meshmessenger.presentation.chat.DialogViewModel
import com.example.meshmessenger.presentation.onboarding.LoginVM
import com.example.meshmessenger.presentation.onboarding.onboarding.RegisterVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val androidModule = module {

    viewModel { LoginVM() }
    viewModel { RegisterVM() }

    single { DatabaseDriverFactory(androidContext()).createDriver() }

    single { AppDatabase(driver = get()) }

    single<UserDataSource> {
        SqlDelightUserDataSource(db = get())
    }

    single<ChatDataSource> {
        SqlDelightChatDataSource(db = get())
    }

    single<ChatMembersDataSource> {
        SqlDelightChatMembersDataSource(db = get())
    }

    single<AttachmentDataSource> {
        SqlDelightAttachmentsDataSource(db = get())
    }

    single<MessageDataSource> {
        SqlDelightMessageDataSource(db = get())
    }


    single<DatabaseRepository> {
        DatabaseRepositoryImpl(
            userDataSource = get(),
            chatDataSource = get(),
            chatMembersDataSource = get(),
            attachmentDataSource = get(),
            messageDataSource = get())
    }

    viewModel {
        DialogViewModel(databaseRepository = get())
    }
}