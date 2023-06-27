package com.example.meshmessenger.presentation.onboarding.login

import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel() {

    var state: CMutableStateFlow<LoginState> =
        MutableStateFlow(LoginState()).cMutableStateFlow()

    private val _actions = Channel<Action>()
    val actions: CFlow<Action> get() = _actions.receiveAsFlow().cFlow()
    val isAnimAccessGrantedPlaying : CMutableStateFlow<Boolean> = MutableStateFlow(false).cMutableStateFlow()

    init {
        val pinValue: String = SharedStorage.secureLoad("pin", "")
        if (pinValue == "") {
            state.value = state.value.copy(
                informText = "Создайте пин"
            )
        } else {
            state.value = state.value.copy(
                informText = "Введите пин"
            )
        }
    }

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.PinChanged -> {
                changePinValue(event.value)
            }
            is LoginEvent.AttemptsChanged -> {
                state.value = state.value.copy(remainingAttempts = state.value.remainingAttempts - 1)
            }
            is LoginEvent.LoginSuccess -> {
                viewModelScope.launch {
                    _actions.send(Action.LoginSuccess)
                }
            }
            is LoginEvent.LoginAttempt -> {
                tryLogin(event.pinAttempt)
            }
            is LoginEvent.AttemptsExceeded -> {
                block()
            }
        }
    }

//    private val _textState: MutableStateFlow<String> = MutableStateFlow(initialTextState)
//    val textState: CStateFlow<String> = _textState.cStateFlow()

    fun tryLogin(pinAttempt: String) {
        viewModelScope.launch {
            if (state.value.remainingAttempts > 0) {
                if (state.value.informText == "Создайте пин") {
                    // засейвили пин в encryptedStorage
                    SharedStorage.secureSave(pinAttempt, "pin")
                    _actions.send(Action.LoginSuccess)
                } else {
                    val savedPin = SharedStorage.secureLoad("pin", "")   // тут выгрузили пин из encryptedStorage
                    if (savedPin == pinAttempt) {
                        isAnimAccessGrantedPlaying.value = true
                        delay(2000)
                        _actions.send(Action.LoginSuccess)
                        isAnimAccessGrantedPlaying.value = false
                    } else {
                        state.value = state.value.copy(
                            informText = "Неверный пин \n осталось ${state.value.remainingAttempts} попыток",
                        )
                        onEvent(LoginEvent.AttemptsChanged)
                    }
                }
            } else {
                onEvent(LoginEvent.AttemptsExceeded)
            }
        }
    }

    private fun block(){
        viewModelScope.launch {
            state.value = state.value.copy(
                keyboardEnabled = false
            )
            for(i in 30 downTo 1){
                state.value = state.value.copy(
                    timer = i,
                    informText = "Mожно повторить \n через $i сек"
                )
                delay(1000)
            }
            state.value = state.value.copy(
                informText = "Cнова введите пин",
                keyboardEnabled = true,
                remainingAttempts = 5
            )
        }
    }

    private fun changePinValue(newValue: String) {
        val pinState = state.value.pinState
        if(pinState.length < 4) {
            if (newValue == "<-") {
                state.value = state.value.copy(
                    pinState = pinState.dropLast(1)
                )
            } else {
                state.value = state.value.copy(
                    pinState = pinState.plus(newValue)
                )
            }
        }
    }

    sealed interface Action {
        object LoginSuccess : Action
        object AttemptsExceeded: Action
    }
}