package io.github.adrirao.notes.feature_auth.presentation.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.adrirao.notes.core.util.Resource
import io.github.adrirao.notes.feature_auth.domain.repository.AuthRepository
import io.github.adrirao.notes.feature_auth.domain.usecase.FieldUseCases
import io.github.adrirao.notes.feature_auth.domain.usecase.remote.AuthUseCases
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val fieldUseCases: FieldUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredEmail -> {
                state = state.copy(
                    email = InputField(
                        value = event.value,
                        validate = fieldUseCases.validateEmail(event.value)
                    )
                )
            }

            is RegisterEvent.EnteredPassword -> {
                state = state.copy(
                    password = InputField(
                        value = event.value,
                        validate = fieldUseCases.validatePassword(event.value)
                    )
                )
            }

            is RegisterEvent.EnteredReEmail -> {
                state = state.copy(
                    reEmail = InputField(
                        value = event.value,
                        validate = fieldUseCases.validateEmails(
                            state.email.value,
                            event.value
                        )
                    )
                )
            }

            is RegisterEvent.EnteredRePassword -> {
                state = state.copy(
                    rePassword = InputField(
                        value = event.value,
                        validate = fieldUseCases.validatePasswords(
                            state.password.value,
                            event.value
                        )
                    )
                )
            }

            RegisterEvent.ToggleVisibilityPassword -> {
                state = state.copy(
                    visibilityPassword = !state.visibilityPassword
                )
            }

            RegisterEvent.ToggleVisibilityRePassword -> {
                state = state.copy(
                    visibilityRePassword = !state.visibilityRePassword
                )
            }

            RegisterEvent.Register -> register()
        }
    }

    private fun register() {

        if (!validateInputs()) return

        enableLoadingButton(enabled = true)

        viewModelScope.launch {
            registerAccount(state.email.value, state.password.value)
            authRepository.verifiedAccount.collect {
                if (it) {
                    enableLoadingButton(enabled = false)
                    verifyAccount()
                    this.cancel()
                }
            }
        }

    }

    private fun validateInputs(): Boolean {
        val validateEmail = fieldUseCases.validateEmail(state.email.value)
        val validateReEmail = fieldUseCases.validateEmails(state.email.value, state.reEmail.value)
        val validatePassword = fieldUseCases.validatePassword(state.password.value)
        val validateRePassword =
            fieldUseCases.validatePasswords(state.password.value, state.rePassword.value)

        val hasError = listOf(
            validateEmail,
            validatePassword,
            validateReEmail,
            validateRePassword
        ).any {
            !it
        }

        if (hasError) {
            state = state.copy(
                email = InputField(
                    value = state.email.value,
                    validate = state.email.validate,
                    messageError = "Error"
                ),
                password = InputField(
                    value = state.password.value,
                    validate = state.password.validate,
                    messageError = "Error"
                ),
                reEmail = InputField(
                    value = state.reEmail.value,
                    validate = state.reEmail.validate,
                    messageError = "Error"
                ),
                rePassword = InputField(
                    value = state.rePassword.value,
                    validate = state.rePassword.validate,
                    messageError = "Error"
                ),
            )
            return false
        }
        return true
    }

    private suspend fun registerAccount(email: String, password: String) {
        when (val result = authUseCases.registerAccount(email, password)) {
            is Resource.Error -> {
                Log.e("registerAccount()", result.uiText.toString())
            }

            is Resource.Success -> {
                sendEmailVerification()
                showVerificationMessage()
            }
        }
    }

    private suspend fun sendEmailVerification() {
        when (val result = authUseCases.sendVerificationEmail()) {
            is Resource.Error -> {
                Log.e("sendEmailVerification()", result.uiText.toString())
            }

            is Resource.Success -> {
                Log.d("sendEmailVerification()", "Email enviado con exito.")
            }
        }
    }

    private fun enableLoadingButton(enabled: Boolean) {
        state = state.copy(
            isLoadingButton = enabled
        )
    }

    private fun showVerificationMessage() {
        state = state.copy(
            verificationMessage = "Mail de verificacion enviado. Por favor revisar correo electronico."
        )
    }

    private fun verifyAccount() {
        state = state.copy(
            isVerifyAccount = true
        )
    }
}
    