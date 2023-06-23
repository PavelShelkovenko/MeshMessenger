package com.example.meshmessenger.presentation.onboarding.registration

data class RegistrationState(
    val email: String = "",
    val emailError: String? = "",
    val password: String = "",
    val passwordError: String? = "",
    val errorText: String? = ""
)
