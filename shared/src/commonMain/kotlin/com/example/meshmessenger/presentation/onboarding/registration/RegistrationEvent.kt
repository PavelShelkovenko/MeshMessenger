package com.example.meshmessenger.presentation.onboarding.registration

sealed class RegistrationEvent {
    data class EmailChanged(val email: String) : RegistrationEvent()
    data class PasswordChanged(val password: String) : RegistrationEvent()

    object SignUp: RegistrationEvent()
}
