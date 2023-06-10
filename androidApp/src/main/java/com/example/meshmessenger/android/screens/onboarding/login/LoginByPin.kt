package com.example.meshmessenger.android.screens.onboarding.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.screens.onboarding.login.keyboard.Keyboard
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.Poppins
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.presentation.onboarding.LoginViewModel
import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.compose.observeAsActions

@Composable
fun LoginByPin(loginViewModel: LoginViewModel, loginSuccess: () -> Unit   ) {

    val textOfState by loginViewModel.textState.collectAsState()
    val isKeyboardEnabled by loginViewModel.isKeyboardEnabled.collectAsState()

    loginViewModel.actions.observeAsActions { action ->
        when(action){
            LoginViewModel.Action.AttemptsExceeded -> { loginViewModel.timer() }
            LoginViewModel.Action.LoginSuccess -> loginSuccess()
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth()
    ) {

        Icon(
            painter = painterResource(id = R.drawable.shield_lock),
            contentDescription = null,
            tint = IconsBlue,
            modifier = Modifier.size(72.dp)
        )

        Text(text = textOfState,
            fontFamily = Poppins,
            color = PrimaryColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(text = SharedStorage.secureLoad("login", "Unknown user"),
            fontFamily = Poppins,
            color = PrimaryColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        PinState(loginViewModel)
    }

    Keyboard(isKeyboardEnabled) { value ->
        loginViewModel.changePinValue(value)
    }
}

@Composable
fun PinState(loginViewModel: LoginViewModel) {

    val pinState: String by loginViewModel.pin.collectAsState()

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (pinState == "") {
            for (i in 0 until 4) {
                Icon(
                    painter = painterResource(id = R.drawable.empty_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        if (pinState.length == 1) {

            for (i in 0 until 1) {
                Icon(
                    painter = painterResource(id = R.drawable.full_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }

            for (i in 0 until 3) {
                Icon(
                    painter = painterResource(id = R.drawable.empty_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        if (pinState.length == 2) {

            for (i in 0 until 2) {
                Icon(
                    painter = painterResource(id = R.drawable.full_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }

            for (i in 0 until 2) {
                Icon(
                    painter = painterResource(id = R.drawable.empty_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        if (pinState.length == 3) {

            for (i in 0 until 3) {
                Icon(
                    painter = painterResource(id = R.drawable.full_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }

            for (i in 0 until 1) {
                Icon(
                    painter = painterResource(id = R.drawable.empty_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        if (pinState.length == 4) {

            for (i in 0 until 4) {
                Icon(
                    painter = painterResource(id = R.drawable.full_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }

            loginViewModel.signIN()
        }
    }
}

