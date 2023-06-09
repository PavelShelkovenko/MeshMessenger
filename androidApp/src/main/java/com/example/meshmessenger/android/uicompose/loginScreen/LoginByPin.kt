package com.example.meshmessenger.android.uicompose.loginScreen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.Poppins
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.presentation.onboarding.LoginVM
import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.compose.observeAsActions

@Composable
fun LoginByPin(viewModel: LoginVM = viewModel(), loginSuccess: () -> Unit   ) {

    val textOfState by viewModel.textState.collectAsState()
    val isKeyboardEnabled by viewModel.isKeyboardEnabled.collectAsState()

    viewModel.actions.observeAsActions { action ->
        when(action){
            LoginVM.Action.AttemptsExceeded -> { viewModel.timer() }
            LoginVM.Action.LoginSuccess -> loginSuccess()
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

        PinState(viewModel)
    }

    Keyboard(isKeyboardEnabled) { value ->
        viewModel.changePinValue(value)
    }
}

