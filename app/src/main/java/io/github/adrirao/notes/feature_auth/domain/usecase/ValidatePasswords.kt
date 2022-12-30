package io.github.adrirao.notes.feature_auth.domain.usecase

class ValidatePasswords {
    operator fun invoke(password: String, rePassword: String): Boolean {
        return password == rePassword
    }
}