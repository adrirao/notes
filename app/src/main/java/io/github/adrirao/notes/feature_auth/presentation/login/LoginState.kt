package io.github.adrirao.notes.feature_auth.presentation.login

data class LoginState(
    val email: InputField = InputField(),
    val password: InputField = InputField(),
    val visibilityPassword: Boolean = false
)

data class InputField(
    val value: String = "",
    val validate: Boolean = true,
    val errorMessage: String = ""
)