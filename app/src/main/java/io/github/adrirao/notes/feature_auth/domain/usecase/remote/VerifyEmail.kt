package io.github.adrirao.notes.feature_auth.domain.usecase.remote

import io.github.adrirao.notes.feature_auth.domain.repository.AuthRepository

class VerifyEmail(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.emailVerified()
}