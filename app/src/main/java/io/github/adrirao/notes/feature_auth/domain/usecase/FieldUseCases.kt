package io.github.adrirao.notes.feature_auth.domain.usecase

data class FieldUseCases(
    val validateEmail: ValidateEmail,
    val validateEmails: ValidateEmails,
    val validatePassword: ValidatePassword,
    val validatePasswords: ValidatePasswords
)