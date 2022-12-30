package io.github.adrirao.notes.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import io.github.adrirao.notes.R
import io.github.adrirao.notes.ui.theme.Background
import io.github.adrirao.notes.ui.theme.Purple40

@Composable
fun NotesBackground(
    @StringRes titleId: Int? = null,
    header: (@Composable BoxScope.() -> Unit)? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            if (header != null) {
                header()
            }else{
                Image(
                    painter = painterResource(id = R.drawable.banner_notes),
                    contentDescription = null,
                    contentScale = ContentScale.Inside
                )
            }

        }

        ConstraintLayout {
            val content = createRef()
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(if (header != null) 400.dp else 700.dp)
                .clip(RoundedCornerShape(topStartPercent = 8, topEndPercent = 8))
                .background(Color.White)
                .padding(10.dp)
                .constrainAs(content) {
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = verticalArrangement,
                    horizontalAlignment = horizontalAlignment
                ) {
                    if (titleId != null) {
                        Text(
                            text = stringResource(id = titleId!!),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun NotesBackgroundPrev() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner_notes),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
        ConstraintLayout {
            val content = createRef()
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(800.dp)
                .clip(RoundedCornerShape(topStartPercent = 8, topEndPercent = 8))
                .background(Color.White)
                .padding(10.dp)
                .constrainAs(content) {
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenido a Notes",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                    NotesEmailTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        placeholder = "Direccion Email"
                    )
                    NotesPasswordTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        onPasswordIconClick = { /*TODO*/ },
                        placeholder = "Password"
                    )
                    NotesButton(
                        text = stringResource(id = R.string.login).toString().uppercase(),
                        onClick = { /*TODO*/ })
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Gray,
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
                    })
                }
            }
        }
    }
}