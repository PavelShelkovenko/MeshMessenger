package com.example.meshmessenger.presentation.onboarding.registration

data class RegistrationState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val errorText: String? = ""
)
