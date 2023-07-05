package com.example.meshmessenger.presentation.onboarding.login

import com.example.meshmessenger.domain.utils.toCommonStateFlow
import com.liftric.kvault.KVault
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val securedStore: KVault,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(LoginState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LoginState()
    ).toCommonStateFlow()

    init {

        val pinValue = securedStore.string(forKey = "pin") ?: ""
        if (pinValue == "") {
            _state.update {
                it.copy(
                    informText = "Создайте пин"
                )
            }
        } else {
            _state.update {
                it.copy(
                    informText = "Введите пин"
                )
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.PinOneElementAdd -> {
                pinOneElementAdd(event.value)
            }
            is LoginEvent.LoginAttempt -> {
                loginAttempt(event.pinAttempt)
            }
            is LoginEvent.LoginSuccess -> {
                _state.update { it.copy(nextScreenNavigation = true) }
            }

            is LoginEvent.PinDropLast -> {
                pinDropLast()
            }

            is LoginEvent.AttemptsExceeded -> {
                block()
            }
        }
    }

    private fun pinOneElementAdd(newValue: String) {
        val pinState = _state.value.pinState
        if(pinState.length < 4){
            _state.update { it.copy(pinState = pinState.plus(newValue)) }
        }
    }

    private fun pinDropLast() {
        val pinState = _state.value.pinState
        _state.update {
            it.copy(pinState = pinState.dropLast(1))
        }
    }

    private fun block(){
        coroutineScope?.launch {
            _state.update {
                it.copy(
                    pinState = "",
                    keyboardEnabled = false
                )
            }
            for(i in 30 downTo 1){
                _state.update {
                    it.copy(
                        timer = i,
                        informText = "Mожно повторить \n через $i сек"
                    )
                }

                delay(1000)
            }
            _state.update {
                it.copy(
                    informText = "Cнова введите пин",
                    keyboardEnabled = true,
                    remainingAttempts = 5
                )
            }
        }
    }

    fun getUserName() = securedStore.string(forKey = "login") ?: "Unknown user"

    private fun loginAttempt(pinAttempt: String) {
        viewModelScope.launch {
            if (_state.value.remainingAttempts > 0) {
                if (_state.value.informText == "Создайте пин") {
                    securedStore.set(key = "pin", stringValue = pinAttempt)
                    _state.update { it.copy(nextScreenNavigation = true) }
                } else {
                    val savedPin = securedStore.string(forKey = "pin")
                    if (savedPin == pinAttempt) {
                        _state.update { it.copy(nextScreenNavigation = true) }
                    }
                    else {
                        _state.update {
                            it.copy(
                                informText = "Неверный пин \n осталось ${state.value.remainingAttempts} попыток",
                                remainingAttempts = state.value.remainingAttempts - 1,
                                pinState = ""
                            )
                        }
                    }
                }
            } else {
                onEvent(LoginEvent.AttemptsExceeded)
            }
        }
    }
}