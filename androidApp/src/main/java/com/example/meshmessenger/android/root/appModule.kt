package com.example.meshmessenger.android.root

import com.example.meshmessenger.onboarding.LoginVM
import com.example.meshmessenger.onboarding.RegisterVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel { LoginVM() }
    viewModel { RegisterVM() }
}