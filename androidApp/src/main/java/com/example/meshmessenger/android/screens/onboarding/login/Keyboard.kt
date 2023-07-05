package com.example.meshmessenger.android.screens.onboarding.login

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.AndroidLoginViewModel
import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.root.stringResource
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.android.theme.ReemKufi
import com.example.meshmessenger.presentation.onboarding.login.LoginEvent


@Composable
fun Keyboard(loginViewModel: AndroidLoginViewModel, isKeyboardEnabled: Boolean, onButtonClick: (String) -> Unit) {

    val activity = LocalContext.current as? Activity

    Box(contentAlignment = BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                SingleKeyboardButton(btnText = "1", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "2", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "3", isKeyboardEnabled, onButtonClick = onButtonClick)
            }
            Row {
                SingleKeyboardButton(btnText = "4", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "5", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "6", isKeyboardEnabled, onButtonClick = onButtonClick)
            }
            Row {
                SingleKeyboardButton(btnText = "7", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "8", isKeyboardEnabled, onButtonClick = onButtonClick)
                SingleKeyboardButton(btnText = "9", isKeyboardEnabled, onButtonClick = onButtonClick)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = { activity?.finish() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BackgroundColor
                    ),
                    modifier = Modifier.padding(all = 10.dp),
                    contentPadding = PaddingValues(vertical = 14.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    ),
                    shape = CircleShape,
                ) {

                    Text(
                        text = stringResource(id = SharedRes.strings.log_out),
                        fontFamily = ReemKufi,
                        color = IconsBlue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                SingleKeyboardButton(btnText = "0", isKeyboardEnabled, onButtonClick = onButtonClick)

                Button(
                    onClick = {
                              loginViewModel.onEvent(LoginEvent.PinDropLast)
                    },
                    colors = ButtonDefaults.buttonColors( backgroundColor = BackgroundColor ),
                    modifier = Modifier.padding(all = 10.dp),
                    contentPadding = PaddingValues( vertical = 14.dp ),

                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    ),
                    shape = CircleShape,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
