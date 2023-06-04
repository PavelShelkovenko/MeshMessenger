package com.example.meshmessenger.presentation.onboarding

import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginVM: ViewModel() {

    private var initialTextState = ""
    init {
        val pinValue: String = SharedStorage.secureLoad("pin", "")

        initialTextState = if(pinValue == ""){
            "Создайте пин"
        } else {
            "Введите пин"
        }
    }

    var currentAttempt : CMutableStateFlow<Int> = MutableStateFlow(5).cMutableStateFlow()

    private val _pin : CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    val pin : CMutableStateFlow<String> = _pin

    private val lostSeconds: CMutableStateFlow<Int> = MutableStateFlow(30).cMutableStateFlow()
    private val isNotLostAttempts: CMutableStateFlow<Boolean> = MutableStateFlow(true).cMutableStateFlow()

    private val _textState: MutableStateFlow<String> = MutableStateFlow(initialTextState)
    val textState: CStateFlow<String> = _textState.cStateFlow()

    private val _isKeyboardEnabled: CMutableStateFlow<Boolean> = MutableStateFlow(true).cMutableStateFlow()
    val isKeyboardEnabled: CMutableStateFlow<Boolean> = _isKeyboardEnabled

    private val _actions = Channel<Action>()
    val actions: CFlow<Action> get() = _actions.receiveAsFlow().cFlow()

    fun signIN(){
        viewModelScope.launch {
            if(currentAttempt.value > 0){
                if (_textState.value == "Создайте пин"){
                    SharedStorage.secureSave(pin.value, "pin")
                    _actions.send(Action.LoginSuccess)
                    currentAttempt.value = 5
                } else {
                    if(pin.value == SharedStorage.secureLoad("pin", "")){
                        _actions.send(Action.LoginSuccess)
                        currentAttempt.value = 5
                    } else {
                        _textState.value = "Неверный пин \n осталось ${currentAttempt.value} попыток"
                        currentAttempt.value = currentAttempt.value - 1
                    }
                }
            } else {
                _actions.send(Action.AttemptsExceeded)
            }
            pin.value = ""
            isKeyboardEnabled.value = true
        }
    }

    fun timer(){
        viewModelScope.launch {
            _isKeyboardEnabled.value = false
            isNotLostAttempts.value = false
            for(i in 30 downTo 1){
                lostSeconds.value = i
                _textState.value = "Mожно повторить \n через ${lostSeconds.value} сек"
                delay(1000)
            }
            _textState.value = "Cнова введите пин"
            isNotLostAttempts.value = true
            _isKeyboardEnabled.value = true
            currentAttempt.value = 5
        }
    }

    fun changePinValue(newValue: String) {
        if (newValue == "<-") {
            _pin.value = pin.value.dropLast(1)
        } else {
            _pin.value = _pin.value.plus(newValue)
        }
    }

    sealed interface Action {
        object LoginSuccess : Action //вошли
        object AttemptsExceeded: Action
    }
}