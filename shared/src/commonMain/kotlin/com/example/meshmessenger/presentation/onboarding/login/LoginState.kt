package com.example.meshmessenger.presentation.onboarding.login

data class LoginState(
    var nextScreenNavigation: Boolean = false,

    val informText: String = "",
    val remainingAttempts: Int = 5,
    val pinState: String = "",
    val timer: Int = 30,
    val keyboardEnabled: Boolean = true
)