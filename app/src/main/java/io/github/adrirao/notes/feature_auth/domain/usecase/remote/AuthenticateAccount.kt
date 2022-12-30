package io.github.adrirao.notes.feature_auth.domain.usecase.remote

import io.github.adrirao.notes.feature_auth.domain.repository.AuthRepository

class AuthenticateAccount(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.authenticate()
}