package io.github.adrirao.notes.feature_auth.presentation.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.adrirao.notes.R
import io.github.adrirao.notes.core.presentation.components.NotesButton
import io.github.adrirao.notes.core.presentation.components.NotesEmailTextField
import io.github.adrirao.notes.core.presentation.components.NotesPasswordTextField
import io.github.adrirao.notes.ui.theme.Purple40

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit
) {
    val state = viewModel.state
    NotesEmailTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.email.value,

        onValueChange = {
            viewModel.onEvent(LoginEvent.EnteredEmail(it))
        },
        placeholder = stringResource(id = R.string.email_address),
        imeAction = ImeAction.Next
    )
    NotesPasswordTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.password.value,
        onValueChange = {
            viewModel.onEvent(LoginEvent.EnteredPassword(it))
        },
        onPasswordIconClick = {
            viewModel.onEvent(LoginEvent.ToggleVisibilityPassword)
        },
        isTextHidden = !state.visibilityPassword,
        placeholder = stringResource(id = R.string.password)
    )
    NotesButton(
        text = stringResource(id = R.string.login).toString().uppercase(),
        onClick = {
            viewModel.onEvent(LoginEvent.Login)
        })
    ClickableText(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Gray,
                fontSize = 14.sp
            )
        ) {
            append(stringResource(R.string.dont_have_account))
            append(" ")
        }
        withStyle(
            style = SpanStyle(
                color = Purple40,
                fontSize = 14.sp
            )
        ) {
            append(stringResource(R.string.sign_up))
        }
    }) {
        onNavigateToRegister()
    }
}