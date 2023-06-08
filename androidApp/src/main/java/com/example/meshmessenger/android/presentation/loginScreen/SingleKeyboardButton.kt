package com.example.meshmessenger.android.presentation.loginScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.android.presentation.theme.BackgroundColor
import com.example.meshmessenger.android.presentation.theme.PrimaryColor
import com.example.meshmessenger.android.presentation.theme.ReemKufi


@Composable
fun SingleKeyboardButton(btnText: String, isKeyboardEnabled: Boolean, onButtonClick: (String) -> Unit) {

    Button(
        onClick = { onButtonClick(btnText) },
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
    SingleKeyboardButton("0",true) {
    }
}