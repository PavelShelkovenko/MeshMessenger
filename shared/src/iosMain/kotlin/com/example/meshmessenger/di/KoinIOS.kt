package com.example.meshmessenger.di

import com.example.meshmessenger.AppInfo
import com.example.meshmessenger.resources.Strings
import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.presentation.channelScreen.ChannelViewModel
import com.example.meshmessenger.presentation.chatScreen.ChatViewModel
import com.example.meshmessenger.presentation.onboarding.login.LoginViewModel
import com.example.meshmessenger.presentation.onboarding.registration.RegistrationViewModel
import com.liftric.kvault.KVault
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

@Suppress("unused")
fun initKoinIos(
    appInfo: AppInfo,
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    module {
        single { appInfo }
        single { doOnStartup }
    }
)

actual val platformModule = module {

    single<SqlDriver> { NativeSqliteDriver(AppDatabase.Schema, "appDatabase.db") }
    single { Strings() }
    single {  KVault() }
    single { LoginViewModel(securedStore = get(), null) }
    single { RegistrationViewModel(sharedStrings = get(), securedStore = get(), null) }
    single { ChannelViewModel(databaseRepository = get()) }
    single { ChatViewModel(databaseRepository = get(), null) }
}

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getRegistrationViewModel() = getKoin().get<RegistrationViewModel>()
    fun getLoginViewModel() = getKoin().get<LoginViewModel>()
    fun getChannelViewModel() = getKoin().get<ChannelViewModel>()
    fun getChatViewModel() = getKoin().get<ChatViewModel>()
}