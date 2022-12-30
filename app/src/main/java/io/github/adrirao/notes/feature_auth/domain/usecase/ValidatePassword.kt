package io.github.adrirao.notes.feature_auth.domain.usecase

class ValidatePassword {
    operator fun invoke(password: String): Boolean {
        return password.length >= 3
//                && password.any { it.isLowerCase() }
//                && password.any { it.isUpperCase() }
//                && password.any { it.isDigit() }
    }
}