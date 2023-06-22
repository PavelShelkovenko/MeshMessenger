package com.example.meshmessenger.presentation.onboarding.registration

class RegistrationValidator() {

     val validateEmail: ValidateEmail = ValidateEmail()
     val validatePassword: ValidatePassword = ValidatePassword()

    data class ValidationResult(
        val successful: Boolean,
        val errorMessage: String? = null
    )

    class ValidateEmail {

        fun execute(email: String): ValidationResult {
            if(email.isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "The email can't be blank"
                )
            }
            if(!email.contains("@")) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "There is no @ in your email"
                )
            }
            if(email.length <= 4) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "Your email is too short"
                )
            }
            if(email.length >= 12) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "Your email is too long"
                )
            }
            return ValidationResult(
                successful = true
            )
        }
    }

    class ValidatePassword {

        fun execute(password: String): ValidationResult {
            if(password.length < 5) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "The password needs to consist of at least 5 characters"
                )
            }
            val containsLettersAndDigits = password.any { it.isDigit() } &&
                    password.any { it.isLetter() }
            if(!containsLettersAndDigits) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "The password needs to contain at least one letter and digit"
                )
            }
            return ValidationResult(
                successful = true
            )
        }
    }
}

