package io.github.adrirao.notes.feature_auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                state = state.copy(
                    email = InputField(
                        value = event.value
                    )
                )
            }

            is LoginEvent.EnteredPassword -> {
                state = state.copy(
                    password = InputField(
                        value = event.value
                    )
                )
            }

            LoginEvent.ToggleVisibilityPassword -> {
                state = state.copy(
                    visibilityPassword = !state.visibilityPassword
                )
            }

            LoginEvent.Login -> login(state.email.value, state.password.value)
        }
    }

    private fun login(email: String, password: String) {

    }
}
    