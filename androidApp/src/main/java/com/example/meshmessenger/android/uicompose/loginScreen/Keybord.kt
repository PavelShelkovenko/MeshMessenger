package com.example.meshmessenger.android.uicompose.loginScreen



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.onboarding.LoginVM


@Composable
fun Keyboard(viewModel: LoginVM) {

    val isKeyboardEnabled: Boolean by viewModel.isKeyboardEnabled.collectAsState()

    Box(contentAlignment = BottomCenter, modifier = Modifier
        .padding(bottom = 20.dp)
        .fillMaxWidth()
        .fillMaxHeight(1f)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row() {
                SingleKeyboardButton(btnText = "1", viewModel) {
                    viewModel.changePinValue("1")
                }
                SingleKeyboardButton(btnText = "2", viewModel){
                    viewModel.changePinValue("2")
                }
                SingleKeyboardButton(btnText = "3", viewModel){
                    viewModel.changePinValue("3")
                }
            }
            Row() {
                SingleKeyboardButton(btnText = "4", viewModel){
                    viewModel.changePinValue("4")
                }
                SingleKeyboardButton(btnText = "5", viewModel){
                    viewModel.changePinValue("5")
                }
                SingleKeyboardButton(btnText = "6", viewModel){
                    viewModel.changePinValue("6")
                }
            }
            Row() {
                SingleKeyboardButton(btnText = "7", viewModel){
                    viewModel.changePinValue("7")
                }
                SingleKeyboardButton(btnText = "8", viewModel){
                    viewModel.changePinValue("8")
                }
                SingleKeyboardButton(btnText = "9", viewModel){
                    viewModel.changePinValue("9")
                }
            }
            Row() {
                Spacer(modifier = Modifier.fillMaxWidth(0.19f))

                SingleKeyboardButton(btnText = "0", viewModel){
                    viewModel.changePinValue("0")
                }

                Button(
                    onClick = {
                        viewModel.changePinValue("")
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
//    Keyboard()
}