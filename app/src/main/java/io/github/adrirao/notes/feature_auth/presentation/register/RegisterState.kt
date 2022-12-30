package io.github.adrirao.notes.feature_auth.presentation.register

data class RegisterState(
    val email: InputField = InputField(),
    val reEmail: InputField = InputField(),
    val password: InputField = InputField(),
    val rePassword: InputField = InputField(),
    val visibilityPassword: Boolean = false,

    val visibilityRePassword: Boolean = false,
    val isLoadingButton: Boolean = false,
    val verificationMessage: String = "",
    val isVerifyAccount: Boolean = false
)

data class InputField(
    val value: String = "",
    val validate: Boolean = false,
    val messageError: String = ""
)