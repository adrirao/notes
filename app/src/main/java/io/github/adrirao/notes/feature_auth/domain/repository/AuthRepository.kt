package io.github.adrirao.notes.feature_auth.domain.repository

import io.github.adrirao.notes.core.util.Resource
import io.github.adrirao.notes.core.util.SimpleResource
import kotlinx.coroutines.flow.Flow

abstract class AuthRepository {
    abstract val verifiedAccount: Flow<Boolean>
    abstract suspend fun register(email: String, password: String): SimpleResource
    abstract suspend fun login(email: String, password: String): SimpleResource
    abstract suspend fun authenticate(): Resource<Boolean>
    abstract suspend fun emailVerified(): Resource<Boolean>
    abstract suspend fun sendEmailVerification(): SimpleResource
}