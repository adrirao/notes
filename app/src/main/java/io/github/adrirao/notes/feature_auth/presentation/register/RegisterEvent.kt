package io.github.adrirao.notes.feature_auth.presentation.register

sealed class RegisterEvent {
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredReEmail(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()
    data class EnteredRePassword(val value: String) : RegisterEvent()

    object ToggleVisibilityPassword : RegisterEvent()
    object ToggleVisibilityRePassword : RegisterEvent()
    object Register : RegisterEvent()
}