package com.example.meshmessenger.android.uicompose.loginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.presentation.onboarding.LoginViewModel

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

@Preview
@Composable
fun PinStatePreview() {
//    PinState()
}