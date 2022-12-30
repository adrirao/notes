package io.github.adrirao.notes.feature_auth.domain.usecase.remote

data class AuthUseCases(
    val authenticateAccount: AuthenticateAccount,
    val loginAccount: LoginAccount,
    val registerAccount: RegisterAccount,
    val verifyEmail: VerifyEmail,
    val sendVerificationEmail: SendVerificationEmail
)