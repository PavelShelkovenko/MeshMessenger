package com.example.meshmessenger.android.presentation.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.presentation.theme.BackgroundColor
import com.example.meshmessenger.android.presentation.theme.PrimaryColor


@Composable
fun Keyboard(isKeyboardEnabled: Boolean, onButtonClick: (String) -> Unit) {

    Box(contentAlignment = BottomCenter, modifier = Modifier
        .padding(bottom = 20.dp)
        .fillMaxWidth()
        .fillMaxHeight(1f)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row() {
                SingleKeyboardButton(btnText = "1", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "2", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "3", isKeyboardEnabled, onButtonClick = onButtonClick)
            }
            Row() {
                SingleKeyboardButton(btnText = "4", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "5", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "6", isKeyboardEnabled, onButtonClick = onButtonClick)
            }
            Row() {
                SingleKeyboardButton(btnText = "7", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "8", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "9", isKeyboardEnabled, onButtonClick = onButtonClick)
            }
            Row() {
                Spacer(modifier = Modifier.fillMaxWidth(0.19f))

                SingleKeyboardButton(btnText = "0", isKeyboardEnabled, onButtonClick = onButtonClick)

                Button(
                    onClick = {
                        onButtonClick("<-")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BackgroundColor
                    ),
                    modifier = Modifier
                        .padding(5.dp),
                    enabled = isKeyboardEnabled,
                    contentPadding = PaddingValues(vertical = 14.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    ),
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier
                            .width(28.dp)
                            .height(36.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun KeyboardPreview() {
    Keyboard(true) {

    }
}