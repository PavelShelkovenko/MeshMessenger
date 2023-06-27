package com.example.meshmessenger.presentation.onboarding.login

sealed class LoginEvent {

    data class PinChanged(val value: String): LoginEvent()
    data class LoginAttempt(val pinAttempt: String): LoginEvent()
    object LoginSuccess: LoginEvent()
    object AttemptsExceeded: LoginEvent()
}