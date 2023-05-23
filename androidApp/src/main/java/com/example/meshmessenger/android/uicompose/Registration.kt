package com.example.meshmessenger.android.uicompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.theme.*
import com.example.meshmessenger.onboarding.RegisterVM
import dev.icerock.moko.mvvm.flow.compose.observeAsActions

@Composable
fun Registration(viewModel: RegisterVM = viewModel(), onLoginSuccess: () -> Unit) {
    val login: String by viewModel.login.collectAsState()
    val password: String by viewModel.password.collectAsState()
    val textOfState: String by viewModel.textOfState.collectAsState()
    val isGoodLogin: Boolean by viewModel.isGoodLogin.collectAsState()
    val isGoodPassword: Boolean by viewModel.isGoodPassword.collectAsState()
    var isPasswordOpen by remember { mutableStateOf(false) }

    viewModel.actions.observeAsActions { action ->
        when (action) {
            is RegisterVM.Action.RegisterSuccess -> onLoginSuccess()
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth()
    ) {

        Text(
            text = "Welcome to MeshApp",
            fontFamily = Poppins,
            color = PrimaryColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = textOfState,
            fontFamily = Poppins,
            color = PrimaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        TextField(
            value = login, onValueChange = {
                viewModel.login.value = it
                viewModel.isDataValid()
            },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = PrimaryColor,
                backgroundColor = Color.White,
                cursorColor = PrimaryColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = InputBoxShape.medium,
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user), contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(6.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(BackgroundColor)
                    )
                }
            },
            placeholder = {
                Text(text = "Username", color = PlaceholderColor)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins
            )
        )

        TextField(
            value = password, onValueChange = {
                viewModel.password.value = it
                viewModel.isDataValid()
            },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = PrimaryColor,
                backgroundColor = Color.White,
                cursorColor = PrimaryColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = InputBoxShape.medium,
            singleLine = true,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(6.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(BackgroundColor)
                    )
                }
            },
            placeholder = {
                Text(text = "Password", color = PlaceholderColor)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                    if (isPasswordOpen) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye_open),
                            contentDescription = "",
                            tint = PrimaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye_close),
                            contentDescription = "",
                            tint = PrimaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        )
        Button(
            onClick = viewModel::signUP,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = PrimaryColor
            ),
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 2.dp
            ),
            enabled = isGoodLogin.and(isGoodPassword),
            shape = CircleShape
        ) {
            Text(
                text = "Sign Up",
                fontFamily = Poppins,
                color = LightPrimaryColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}