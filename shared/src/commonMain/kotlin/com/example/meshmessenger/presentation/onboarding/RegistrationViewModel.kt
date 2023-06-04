package com.example.meshmessenger.presentation.onboarding.onboarding

import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class RegisterVM : ViewModel() {

    private val _isGoodLogin : CMutableStateFlow<Boolean> = MutableStateFlow(false).cMutableStateFlow()
    var isGoodLogin : CStateFlow<Boolean> = _isGoodLogin.cStateFlow()

    private val _isGoodPassword : CMutableStateFlow<Boolean> = MutableStateFlow(false).cMutableStateFlow()
    val isGoodPassword : CStateFlow<Boolean> = _isGoodPassword.cStateFlow()

    val login : CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    val password : CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()

    private val _textOfState: CMutableStateFlow<String> = MutableStateFlow("Write your data, pls").cMutableStateFlow()
    val textOfState: CStateFlow<String> = _textOfState.cStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    //val isLoading: CStateFlow<Boolean> = _isLoading.cStateFlow()

    private val _actions = Channel<Action>()
    val actions: CFlow<Action> get() = _actions.receiveAsFlow().cFlow()

    fun signUP(){
        _isLoading.value = true
        viewModelScope.launch {
            SharedStorage.secureSave(login.value, "login")      //шифруем данные
            SharedStorage.secureSave(password.value, "password")//и записываем в encrypted sp
            _actions.send(Action.RegisterSuccess)
            _isLoading.value = false
        }
    }

    fun isDataValid() {
        viewModelScope.launch {
            if(login.value.isEmpty()){
                _textOfState.value = "Логин не должен быть пустым"
                _isGoodLogin.value = false
            } else if(!login.value.contains("@")){
                _textOfState.value = "Неверный логин, нет @"
                _isGoodLogin.value = false
            } else if(login.value.length < 5){
                _isGoodLogin.value = false
                _textOfState.value = "Короткий логин"
            }else if(login.value.length > 20){
                _textOfState.value = "Длинный логин"
                _isGoodLogin.value = false
            } else if(password.value.isEmpty()){
                _textOfState.value = "Пароль не должен быть пустым"
                _isGoodPassword.value = false
            } else if(password.value.length < 5){
                _textOfState.value = "Короткий пароль"
                _isGoodPassword.value = false
            } else if(password.value.length > 20){
                _textOfState.value = "Длинный пароль"
                _isGoodPassword.value = false
            } else if(password.value.length > 20){
                _textOfState.value = "длинный пароль"
                _isGoodPassword.value = false
            } else {
                _isGoodLogin.value = true
                _isGoodPassword.value = true
                _textOfState.value = ""
            }
        }
    }

    interface Action {
        object RegisterSuccess: Action
    }
}