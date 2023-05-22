package com.example.meshmessenger

import com.example.meshmessenger.onboarding.LoginVM
import com.example.meshmessenger.onboarding.RegisterVM
import org.koin.dsl.module

actual fun platformModule() = module {
    single { LoginVM() }
    single { RegisterVM() }
}