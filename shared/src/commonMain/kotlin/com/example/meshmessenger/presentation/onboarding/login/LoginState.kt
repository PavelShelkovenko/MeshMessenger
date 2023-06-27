package com.example.meshmessenger.presentation.onboarding.login

data class LoginState(
    val informText: String = "",
    val remainingAttempts: Int = 6,
    val pinState: String = "",
    val timer: Int = 30,
    val keyboardEnabled: Boolean = true
)