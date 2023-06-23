package com.example.meshmessenger.presentation.onboarding.registration

import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.Strings
import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class RegistrationViewModel(private val sharedStrings: Strings) : ViewModel() {

    private val validator: RegistrationValidator = RegistrationValidator(sharedStrings)

    var state: CMutableStateFlow<RegistrationState> =
        MutableStateFlow(RegistrationState()).cMutableStateFlow()

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
            is RegistrationEvent.ErrorTextChanged -> {
                state.value = state.value.copy(errorText = event.error)
            }
            is RegistrationEvent.SignUp -> {
                signUp()
            }
        }
    }


    fun validateEmail(): Boolean {
        val emailResult = validator.validateEmail.execute(state.value.email)
        val emailError = emailResult.errorMessage
        if (emailError != null) {
            state.value = state.value.copy(
                emailError = emailError,
                errorText = emailError
            )
            return false
        }
        state.value = state.value.copy(
            emailError = emailError,
            errorText = sharedStrings.get(SharedRes.strings.empty_string, listOf())
        )
        return true
    }

    fun validatePassword(): Boolean {
        val passwordResult = validator.validatePassword.execute(state.value.password)
        val passwordError = passwordResult.errorMessage
        if (passwordError != null) {
            state.value = state.value.copy(
                passwordError = passwordError,
                errorText = passwordError
            )
            return false
        }
        state.value = state.value.copy(
            passwordError = passwordError,
            errorText = sharedStrings.get(SharedRes.strings.empty_string, listOf())
        )
        return true
    }

    fun validateData(): Boolean {
        if (state.value.emailError == null && state.value.passwordError == null) {
            state.value = state.value.copy(
                errorText = sharedStrings.get(SharedRes.strings.join_us, listOf())
            )
            return true
        }
        return false
    }

    fun signUp() {
        viewModelScope.launch {
            SharedStorage.secureSave(state.value.email, "login")      //шифруем данные
            SharedStorage.secureSave(state.value.password, "password")//и записываем в encrypted sp
            _actions.send(Action.RegistrationSuccess)
        }
    }


    interface Action {
        object RegistrationSuccess: Action
    }
}