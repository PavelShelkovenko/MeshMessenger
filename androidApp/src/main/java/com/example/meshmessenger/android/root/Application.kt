package com.example.meshmessenger.android.root

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.meshmessenger.AppInfo
import com.example.meshmessenger.android.BuildConfig
import com.example.meshmessenger.di.initKoin
import com.example.meshmessenger.presentation.chat.DialogViewModel
import com.example.meshmessenger.presentation.onboarding.LoginVM
import com.example.meshmessenger.presentation.onboarding.onboarding.RegisterVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


class Application : Application()  {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                single<Context> { this@Application }
                single<AppInfo> { AndroidAppInfo }
                viewModel { LoginVM() }
                viewModel { RegisterVM() }
                viewModel { DialogViewModel(databaseRepository = get()) }
                single {
                    { Log.i("Startup", "Hello from Android/Kotlin!") }
                }
            }
        )
    }
}

object AndroidAppInfo : AppInfo {
    override val appId: String = BuildConfig.APPLICATION_ID
}