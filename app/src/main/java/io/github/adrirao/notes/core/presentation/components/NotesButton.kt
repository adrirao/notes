package io.github.adrirao.notes.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotesButton(
    text: String,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier,
        onClick = {
            if (!isLoading) {
                onClick()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Black,
            contentColor = White
        ),
        shape = RoundedCornerShape(if (text.length == 1) 16.dp else 38.dp),
        enabled = enabled
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = White)
        } else {
            Text(
                text = text,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                fontWeight = fontWeight,
                maxLines = 1,
                fontSize = fontSize
            )
        }
    }
}