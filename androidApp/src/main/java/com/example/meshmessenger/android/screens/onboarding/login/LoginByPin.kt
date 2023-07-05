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
import com.example.meshmessenger.AndroidLoginViewModel
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.screens.onboarding.login.keyboard.Keyboard
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.Poppins
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.presentation.onboarding.login.LoginEvent
import com.example.meshmessenger.presentation.onboarding.login.LoginState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginByPin(loginViewModel: AndroidLoginViewModel = koinViewModel(), loginSuccess: () -> Unit) {

    val state by loginViewModel.state.collectAsState()

    if (state.nextScreenNavigation) {
        loginSuccess()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.shield_lock),
            contentDescription = null,
            tint = IconsBlue,
            modifier = Modifier.size(72.dp)
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth()
    ) {

        Text(
            text = state.informText,
            fontFamily = Poppins,
            color = PrimaryColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = loginViewModel.getUserName(),
            fontFamily = Poppins,
            color = PrimaryColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        PinState(state) { pinAttempt ->
            loginViewModel.onEvent(LoginEvent.LoginAttempt(pinAttempt))
        }
    }

    Keyboard(state.keyboardEnabled) { value ->
        loginViewModel.onEvent(LoginEvent.PinChanged(value))
    }
}

@Composable
fun PinState(state: LoginState, loginAttempt: (String) -> Unit) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.pinState.length == 4) {
            loginAttempt(state.pinState)
        }
        repeat(4) {

            if (it < state.pinState.length) {
                Icon(
                    painter = painterResource(id = R.drawable.full_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.empty_circle),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

