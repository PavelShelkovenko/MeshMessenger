package com.example.meshmessenger.presentation.onboarding.registration

import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.domain.utils.toCommonStateFlow
import com.example.meshmessenger.resources.Strings
import com.liftric.kvault.KVault
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class RegistrationViewModel(
    private val sharedStrings: Strings,
    private val securedStore: KVault,
    private val coroutineScope: CoroutineScope?
) {

    private val validator: RegistrationValidator = RegistrationValidator(sharedStrings)
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        RegistrationState()
    ).toCommonStateFlow()

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.EmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is RegistrationEvent.PasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is RegistrationEvent.ErrorTextChanged -> {
                _state.update {
                    it.copy(
                        errorText = event.error
                    )
                }
            }
            is RegistrationEvent.SignUp -> {
                signUp()
            }
        }
    }


    fun validateEmail() {
        val emailResult = validator.validateEmail.execute(_state.value.email)
        val emailError = emailResult.errorMessage
        if (emailError != null) {
            _state.update {
                it.copy(
                    emailError = emailError,
                    errorText = emailError
                )
            }
            return
        }
        _state.update {
            it.copy(
                emailError = emailError,
                errorText = sharedStrings.get(SharedRes.strings.empty_string, listOf())
            )
        }
        return
    }

    fun validatePassword() {
        val passwordResult = validator.validatePassword.execute(_state.value.password)
        val passwordError = passwordResult.errorMessage
        if (passwordError != null) {
            _state.update {
                it.copy(
                    passwordError = passwordError,
                    errorText = passwordError
                )
            }
            return
        }
        _state.update {
            it.copy(
                passwordError = passwordError,
                errorText = sharedStrings.get(SharedRes.strings.empty_string, listOf())
            )
        }
        return
    }

    fun validateData(): Boolean {
        if (_state.value.emailError == "" && _state.value.passwordError == "") {
            _state.update {
                it.copy(
                    errorText = sharedStrings.get(SharedRes.strings.write_your_data, listOf())
                )
            }
            return false
        }
        if (state.value.emailError == null && _state.value.passwordError == null) {
            _state.update {
                it.copy(
                    errorText = sharedStrings.get(SharedRes.strings.join_us, listOf())
                )
            }
            return true
        }
        return false
    }

    private fun signUp() {
        viewModelScope.launch {
            securedStore.set(key = "login", stringValue = _state.value.email)
            securedStore.set(key = "password", stringValue = _state.value.password)
        }
    }
}