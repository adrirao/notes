package io.github.adrirao.notes.feature_auth.presentation.login

sealed class LoginEvent {
    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()

    object ToggleVisibilityPassword : LoginEvent()
    object Login : LoginEvent()
}