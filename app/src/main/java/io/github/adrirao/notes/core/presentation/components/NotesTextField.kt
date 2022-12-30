package io.github.adrirao.notes.core.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.adrirao.notes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    showError: Boolean = false,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    trailingIcon: ImageVector? = null,
    trailingIconColor: Color? = null,
    onIconClick: () -> Unit = {},
    isPassword: Boolean = false,
    isTextHidden: Boolean = false,
    isValid: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        placeholder = {
            Text(text = placeholder)
        },
        colors = TextFieldDefaults.textFieldColors(
            errorCursorColor = Red,
            errorIndicatorColor = Red,
            errorLabelColor = Red,
            textColor = DarkGray,
            placeholderColor = Gray,
            containerColor = Color(0xFFF2F3F7),
            focusedIndicatorColor = Gray,
            unfocusedIndicatorColor = Transparent,
            unfocusedTrailingIconColor = if (isValid) Green else trailingIconColor ?: Gray,
            cursorColor = DarkGray,
            focusedTrailingIconColor = if (isValid) Green else trailingIconColor ?: Gray,
        ),
        isError = showError,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        trailingIcon = {
            TrailingIcon(
                isPassword = isPassword,
                trailingIcon = trailingIcon,
                onIconClick = onIconClick,
                isValid = isValid,
                isTextHidden = isTextHidden
            )
        },
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isTextHidden) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
fun NotesPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onPasswordIconClick: () -> Unit,
    placeholder: String = "",
    showError: Boolean = false,
    isValid: Boolean = false,
    isTextHidden: Boolean = true,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
) {
    NotesTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        showError = showError,
        modifier = modifier,
        keyboardType = KeyboardType.Password,
        isPassword = true,
        onIconClick = onPasswordIconClick,
        isTextHidden = isTextHidden,
        isValid = isValid
    )
}

@Composable
fun NotesEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    showError: Boolean = false,
    isValid: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    modifier: Modifier = Modifier
) {
    NotesTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        showError = showError,
        modifier = modifier,
        keyboardType = KeyboardType.Email,
        isValid = isValid,
        imeAction = imeAction
    )
}

@Composable
private fun TrailingIcon(
    isPassword: Boolean = false,
    isTextHidden: Boolean = false,
    trailingIcon: ImageVector? = null,
    isValid: Boolean = false,
    onIconClick: () -> Unit = {}
) {
    if (trailingIcon != null) {
        IconButton(onClick = onIconClick) {
            Icon(
                imageVector = trailingIcon,
                contentDescription = stringResource(id = R.string.icon)
            )
        }
    } else if (isPassword) {
        IconButton(onClick = onIconClick) {
            val icon =
                if (isTextHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.toggle_password_visibility)
            )
        }
    } else if (isValid) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(id = R.string.valid_input),
            tint = Gray
        )
    }
}
