package com.example.meshmessenger.presentation.onboarding.login

sealed class LoginEvent {

    data class PinOneElementAdd(val value: String): LoginEvent()
    data class LoginAttempt(val pinAttempt: String): LoginEvent()
    object PinDropLast: LoginEvent()

    object LoginSuccess: LoginEvent()
    object AttemptsExceeded: LoginEvent()
}