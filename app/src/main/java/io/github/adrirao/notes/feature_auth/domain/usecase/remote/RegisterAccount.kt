package io.github.adrirao.notes.feature_auth.domain.usecase.remote

import io.github.adrirao.notes.feature_auth.domain.repository.AuthRepository

class RegisterAccount(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.register(email, password)
}