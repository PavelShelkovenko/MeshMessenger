package com.example.meshmessenger

import com.example.meshmessenger.presentation.onboarding.LoginVM
import com.example.meshmessenger.presentation.onboarding.onboarding.RegisterVM
import org.koin.dsl.module

actual fun platformModule() = module {
    single { LoginVM() }
    single { RegisterVM() }
}