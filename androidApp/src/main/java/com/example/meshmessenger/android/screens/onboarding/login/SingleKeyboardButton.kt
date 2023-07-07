package com.example.meshmessenger.android.screens.onboarding.login

import androidx.compose.foundation.BorderStroke
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
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.Onest
import com.example.meshmessenger.android.theme.PrimaryColor

@Composable
fun SingleKeyboardButton(btnText: String, isKeyboardEnabled: Boolean, onButtonClick: (String) -> Unit) {

    Button(
        onClick = { onButtonClick(btnText) },
        colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColor),
        modifier = Modifier.padding( all = 5.dp ),
        shape = CircleShape,
        border = BorderStroke(1.dp, IconsBlue) ,
        enabled = isKeyboardEnabled
    ) {
        Text(
            modifier = Modifier.padding(all = 20.dp),
            text = btnText,
            fontFamily = Onest,
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