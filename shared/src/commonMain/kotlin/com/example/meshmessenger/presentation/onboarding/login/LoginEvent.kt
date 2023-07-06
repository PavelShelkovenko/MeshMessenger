package com.example.meshmessenger.presentation.onboarding.login

sealed class LoginEvent {

    data class PinOneElementAdd(val value: String): LoginEvent()
    object PinDropLast: LoginEvent()

    object LoginSuccess: LoginEvent()
}