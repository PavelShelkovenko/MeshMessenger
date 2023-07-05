package com.example.meshmessenger.android.screens.onboarding.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.* import androidx.compose.ui.text.style.TextAlign
import com.example.meshmessenger.AndroidRegistrationViewModel
import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.root.colorResource
import com.example.meshmessenger.android.root.fontFamilyResource
import com.example.meshmessenger.android.root.stringResource
import com.example.meshmessenger.android.theme.*
import com.example.meshmessenger.presentation.onboarding.registration.RegistrationEvent


@Composable
fun Registration(registrationViewModel: AndroidRegistrationViewModel, onAccountCreated: () -> Unit) {

    val state by registrationViewModel.state.collectAsState()

    var isPasswordOpen by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(id = SharedRes.strings.welcome),
            fontFamily = fontFamilyResource(fontResource = SharedRes.fonts.Onest.onest),
            color = colorResource(resource = SharedRes.colors.PrimaryColor),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            text = state.errorText ?: "",
            fontFamily = fontFamilyResource(fontResource = SharedRes.fonts.Onest.onest),
            color = colorResource(resource = SharedRes.colors.PrimaryColor),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        TextField(
            value = state.email,
            onValueChange = {
                registrationViewModel.onEvent(RegistrationEvent.EmailChanged(it))
                registrationViewModel.validateEmail()
                registrationViewModel.validateData()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(resource = SharedRes.colors.PrimaryColor),
                backgroundColor = colorResource(resource = SharedRes.colors.White),
                cursorColor = colorResource(resource = SharedRes.colors.PrimaryColor),
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
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "",
                        tint = colorResource(resource = SharedRes.colors.PrimaryColor),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                    Spacer(modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(colorResource(resource = SharedRes.colors.PrimaryColor))
                    )
                }
            },
            placeholder = {
                Text(text = stringResource(id = SharedRes.strings.username), color = PlaceholderColor)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamilyResource(fontResource = SharedRes.fonts.Onest.onest)
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
        )

        TextField(
            value = state.password,
            onValueChange = {
                registrationViewModel.onEvent(RegistrationEvent.PasswordChanged(it))
                registrationViewModel.validatePassword()
                registrationViewModel.validateData()
            },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(resource = SharedRes.colors.PrimaryColor),
                backgroundColor = colorResource(resource = SharedRes.colors.White),
                cursorColor = colorResource(resource = SharedRes.colors.PrimaryColor),
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
                        contentDescription = "password icon",
                        tint = colorResource(resource = SharedRes.colors.PrimaryColor),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer( modifier = Modifier.width(6.dp)  )

                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(colorResource(resource = SharedRes.colors.PrimaryColor))
                    )
                }
            },
            placeholder = {
                Text(
                    text = stringResource(id = SharedRes.strings.password),
                    color = PlaceholderColor
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamilyResource(fontResource = SharedRes.fonts.Onest.onest)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onNext = { focusManager.clearFocus() }),
            visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                    if (isPasswordOpen) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye_open),
                            contentDescription = "",
                            tint = colorResource(resource = SharedRes.colors.PrimaryColor),
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye_close),
                            contentDescription = "",
                            tint = colorResource(resource = SharedRes.colors.PrimaryColor),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        )

        Button(
            onClick = {
                registrationViewModel.onEvent(RegistrationEvent.SignUp)
                onAccountCreated()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(resource = SharedRes.colors.PrimaryColor)
            ),
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 2.dp
            ),
            shape = CircleShape,
            enabled = registrationViewModel.validateData()
        ) {
            Text(
                text = stringResource(id = SharedRes.strings.sign_up),
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                fontFamily = fontFamilyResource(fontResource = SharedRes.fonts.Onest.onest),
                color = colorResource(resource = SharedRes.colors.LightPrimaryColor),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}