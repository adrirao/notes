package io.github.adrirao.notes.feature_auth.domain.usecase.remote

import io.github.adrirao.notes.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginAccount(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.login(email, password)
}