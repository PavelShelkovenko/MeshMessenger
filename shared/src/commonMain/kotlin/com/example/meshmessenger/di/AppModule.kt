package com.example.meshmessenger.di


import com.example.meshmessenger.AppInfo
import com.example.meshmessenger.data.attachment.SqlDelightAttachmentsDataSource
import com.example.meshmessenger.data.chat_members.SqlDelightChatMembersDataSource
import com.example.meshmessenger.data.chats.SqlDelightChatDataSource
import com.example.meshmessenger.data.database_repository.DatabaseRepositoryImpl
import com.example.meshmessenger.data.message.SqlDelightMessageDataSource
import com.example.meshmessenger.data.user.SqlDelightUserDataSource
import com.example.meshmessenger.domain.models.attachment.AttachmentDataSource
import com.example.meshmessenger.domain.models.chat.ChatDataSource
import com.example.meshmessenger.domain.models.chat_members.ChatMembersDataSource
import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import com.example.meshmessenger.domain.models.message.MessageDataSource
import com.example.meshmessenger.domain.models.user.UserDataSource
import com.example.meshmessenger.presentation.onboarding.registration.RegistrationValidator
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            platformModule,
            coreModule
        )
    }

    // Dummy initialization logic, making use of appModule declarations for demonstration purposes.
    val koin = koinApplication.koin
    // doOnStartup is a lambda which is implemented in Swift on iOS side
    val doOnStartup = koin.get<() -> Unit>()
    doOnStartup.invoke()

    // AppInfo is a Kotlin interface with separate Android and iOS implementations
    val appInfo = koin.get<AppInfo>()

    return koinApplication
}

private val coreModule = module {

    single<DatabaseRepository> {
        DatabaseRepositoryImpl(
            userDataSource = get(),
            chatMembersDataSource = get(),
            chatDataSource = get(),
            attachmentDataSource = get(),
            messageDataSource = get()
        )
    }

    single<UserDataSource> {
        SqlDelightUserDataSource(sqlDriver = get())
    }

    single<ChatDataSource> {
        SqlDelightChatDataSource(sqlDriver = get())
    }

    single<ChatMembersDataSource> {
        SqlDelightChatMembersDataSource(sqlDriver = get())
    }

    single<AttachmentDataSource> {
        SqlDelightAttachmentsDataSource(sqlDriver = get())
    }

    single<MessageDataSource> {
        SqlDelightMessageDataSource(sqlDriver = get())
    }

    single {
        RegistrationValidator(sharedString = get())
    }

}

expect val platformModule: Module