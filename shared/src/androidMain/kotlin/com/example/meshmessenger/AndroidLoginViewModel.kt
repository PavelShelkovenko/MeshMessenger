package com.example.meshmessenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meshmessenger.presentation.onboarding.login.LoginEvent
import com.example.meshmessenger.presentation.onboarding.login.LoginViewModel
import com.liftric.kvault.KVault

class AndroidLoginViewModel(
    private val securedStore: KVault
) : ViewModel() {

    private val viewModel by lazy {
        LoginViewModel(
            securedStore = securedStore,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: LoginEvent){
        return viewModel.onEvent(event)
    }
}
