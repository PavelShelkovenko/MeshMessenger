package com.example.meshmessenger.presentation.onboarding.registration

import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.Strings

class RegistrationValidator(sharedString: Strings) {

     val validateEmail: ValidateEmail = ValidateEmail(sharedString)
     val validatePassword: ValidatePassword = ValidatePassword(sharedString)

    data class ValidationResult(
        val successful: Boolean,
        val errorMessage: String? = null
    )

    class ValidateEmail(private val sharedString: Strings) {

        fun execute(email: String): ValidationResult {
            if(email.isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = sharedString.get(SharedRes.strings.blank_email, listOf())
                )
            }
            if(!email.contains("@")) {
                return ValidationResult(
                    successful = false,
                    errorMessage = sharedString.get(SharedRes.strings.no_email_char, listOf())
                )
            }
            if(email.length <= 4) {
                return ValidationResult(
                    successful = false,
                    errorMessage = sharedString.get(SharedRes.strings.short_email, listOf())
                )
            }
            if(email.length >= 12) {
                return ValidationResult(
                    successful = false,
                    errorMessage = sharedString.get(SharedRes.strings.long_email, listOf())
                )
            }
            return ValidationResult(
                successful = true,
                errorMessage = null
            )
        }
    }

    class ValidatePassword(private val sharedString: Strings) {

        fun execute(password: String): ValidationResult {
            if(password.length < 5) {
                return ValidationResult(
                    successful = false,
                    errorMessage = sharedString.get(SharedRes.strings.short_password, listOf())
                )
            }
            val containsLettersAndDigits = password.any { it.isDigit() } &&
                    password.any { it.isLetter() }
            if(!containsLettersAndDigits) {
                return ValidationResult(
                    successful = false,
                    errorMessage = sharedString.get(SharedRes.strings.one_letter_one_digit_password, listOf())
                )
            }
            return ValidationResult(
                successful = true,
                errorMessage = null
            )
        }
    }
}

