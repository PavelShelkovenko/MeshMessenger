package com.example.meshmessenger.android.screens.onboarding.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.presentation.onboarding.login.LoginState

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