package com.example.meshmessenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meshmessenger.presentation.onboarding.registration.RegistrationEvent
import com.example.meshmessenger.presentation.onboarding.registration.RegistrationViewModel
import com.example.meshmessenger.resources.Strings
import com.liftric.kvault.KVault

class AndroidRegistrationViewModel(
    private val sharedStrings : Strings,
    private val securedStore: KVault
): ViewModel() {

    private val viewModel by lazy {
        RegistrationViewModel(
            sharedStrings = sharedStrings,
            securedStore = securedStore,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: RegistrationEvent) {
        viewModel.onEvent(event)
    }

    fun validateEmail() {
       return viewModel.validateEmail()
    }

    fun validatePassword() {
        return viewModel.validatePassword()
    }

    fun validateData(): Boolean {
        return viewModel.validateData()
    }

}