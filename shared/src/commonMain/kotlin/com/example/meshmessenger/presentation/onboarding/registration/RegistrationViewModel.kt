package com.example.meshmessenger.presentation.onboarding.registration

import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class RegistrationViewModel(private val validator: RegistrationValidator) : ViewModel() {

    var state: CMutableStateFlow<RegistrationState> =
        MutableStateFlow(RegistrationState()).cMutableStateFlow()

    //    private val _isGoodLogin : CMutableStateFlow<Boolean> = MutableStateFlow(false).cMutableStateFlow()
//    var isGoodLogin : CStateFlow<Boolean> = _isGoodLogin.cStateFlow()
//
//    private val _isGoodPassword : CMutableStateFlow<Boolean> = MutableStateFlow(false).cMutableStateFlow()
//    val isGoodPassword : CStateFlow<Boolean> = _isGoodPassword.cStateFlow()
//
//    val login : CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
//    val password : CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
//
//    private val _textOfState: CMutableStateFlow<String> = MutableStateFlow("Write your data, pls").cMutableStateFlow()
//    val textOfState: CStateFlow<String> = _textOfState.cStateFlow()
//
//    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
//    //val isLoading: CStateFlow<Boolean> = _isLoading.cStateFlow()

    private val _actions = Channel<Action>()
    val actions: CFlow<Action> get() = _actions.receiveAsFlow().cFlow()

    fun onEvent(event: RegistrationEvent) {
        when(event) {
            is RegistrationEvent.EmailChanged -> {
                state.value = state.value.copy(email = event.email)
            }
            is RegistrationEvent.PasswordChanged -> {
                state.value = state.value.copy(password = event.password)
            }
            is RegistrationEvent.SignUp -> {
                signUp()
            }
        }
    }

    fun signUp(){

        val emailResult = validator.validateEmail.execute(state.value.email)
        val passwordResult = validator.validatePassword.execute(state.value.password)

        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any { !it.successful }

        if(hasError) {
            state.value = state.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            SharedStorage.secureSave(state.value.email, "login")      //шифруем данные
            SharedStorage.secureSave(state.value.password, "password")//и записываем в encrypted sp
            _actions.send(Action.RegistrationSuccess)
        }
    }

//    fun isDataValid() {
//        viewModelScope.launch {
//            if(login.value.isEmpty()){
//                _textOfState.value = "Логин не должен быть пустым"
//                _isGoodLogin.value = false
//            } else if(!login.value.contains("@")){
//                _textOfState.value = "Неверный логин, нет @"
//                _isGoodLogin.value = false
//            } else if(login.value.length < 5){
//                _isGoodLogin.value = false
//                _textOfState.value = "Короткий логин"
//            }else if(login.value.length > 20){
//                _textOfState.value = "Длинный логин"
//                _isGoodLogin.value = false
//            } else if(password.value.isEmpty()){
//                _textOfState.value = "Пароль не должен быть пустым"
//                _isGoodPassword.value = false
//            } else if(password.value.length < 5){
//                _textOfState.value = "Короткий пароль"
//                _isGoodPassword.value = false
//            } else if(password.value.length > 20){
//                _textOfState.value = "Длинный пароль"
//                _isGoodPassword.value = false
//            } else if(password.value.length > 20){
//                _textOfState.value = "длинный пароль"
//                _isGoodPassword.value = false
//            } else {
//                _isGoodLogin.value = true
//                _isGoodPassword.value = true
//                _textOfState.value = "Скорее  присоединяйся!"
//            }
//        }
//    }

    interface Action {
        object RegistrationSuccess: Action
    }
}