package com.example.meshmessenger.android.uicompose.loginScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.android.theme.*
import com.example.meshmessenger.onboarding.LoginVM


@Composable
fun SingleKeyboardButton(btnText: String, viewModel: LoginVM, onButtonClick: () -> Unit) {

    val isKeyboardEnabled: Boolean by viewModel.isKeyboardEnabled.collectAsState()

    Button(
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BackgroundColor
        ),
        modifier = Modifier
            .padding(5.dp),
        contentPadding = PaddingValues(vertical = 14.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 2.dp
        ),
        shape = CircleShape,
        enabled = isKeyboardEnabled
    ) {
        Text(
            text = btnText,
            fontFamily = ReemKufi,
            color = PrimaryColor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun KeyboardButtonPreview() {
//    SingleKeyboardButton("0",) {
//    }
}