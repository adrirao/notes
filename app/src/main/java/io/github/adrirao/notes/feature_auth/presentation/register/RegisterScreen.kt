package io.github.adrirao.notes.feature_auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.adrirao.notes.R
import io.github.adrirao.notes.core.presentation.components.NotesButton
import io.github.adrirao.notes.core.presentation.components.NotesEmailTextField
import io.github.adrirao.notes.core.presentation.components.NotesPasswordTextField
import io.github.adrirao.notes.core.presentation.components.NotesSpacer
import io.github.adrirao.notes.core.presentation.components.NotesTextField
import io.github.adrirao.notes.feature_auth.presentation.login.LoginEvent
import io.github.adrirao.notes.ui.theme.Purple40

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val state = viewModel.state

    LaunchedEffect(key1 = state.isVerifyAccount) {
        if (state.isVerifyAccount) {
            onNavigateToHome()
        }
    }

    Column {
        NotesSpacer()
        NotesEmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email.value,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredEmail(it))
            },
            placeholder = stringResource(id = R.string.email_address),
            isValid = state.email.validate,
            showError = state.email.messageError.isNotBlank(),
            imeAction = ImeAction.Next
        )
        NotesSpacer()
        NotesEmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.reEmail.value,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredReEmail(it))
            },
            placeholder = stringResource(id = R.string.re_email_address),
            isValid = state.reEmail.validate,
            showError = state.reEmail.messageError.isNotBlank(),
            imeAction = ImeAction.Next
        )
        NotesSpacer()
        NotesPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password.value,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredPassword(it))
            },
            onPasswordIconClick = {
                viewModel.onEvent(RegisterEvent.ToggleVisibilityPassword)
            },
            placeholder = stringResource(id = R.string.password),
            isTextHidden = !state.visibilityPassword,
            showError = state.password.messageError.isNotBlank(),
            imeAction = ImeAction.Next
        )
        NotesSpacer()
        NotesPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.rePassword.value,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredRePassword(it))
            },
            onPasswordIconClick = {
                viewModel.onEvent(RegisterEvent.ToggleVisibilityRePassword)
            },
            placeholder = stringResource(id = R.string.re_password),
            isTextHidden = !state.visibilityRePassword,
            showError = state.rePassword.messageError.isNotBlank(),
            imeAction = ImeAction.Done
        )
    }
    NotesSpacer()
    NotesButton(
        text = stringResource(id = R.string.register).toString().uppercase(),
        onClick = {
            viewModel.onEvent(RegisterEvent.Register)
        },
        isLoading = state.isLoadingButton,
        enabled = !state.isLoadingButton
    )
    NotesSpacer()

    if (state.verificationMessage.isEmpty()) {
        ClickableText(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            ) {
                append(stringResource(R.string.have_account))
                append(" ")
            }
            withStyle(
                style = SpanStyle(
                    color = Purple40,
                    fontSize = 14.sp
                )
            ) {
                append(stringResource(R.string.login))
            }
        }) {
            onNavigateToLogin()
        }
    } else {
        Text(text = state.verificationMessage, color = Color.Gray, textAlign = TextAlign.Center)
    }
}