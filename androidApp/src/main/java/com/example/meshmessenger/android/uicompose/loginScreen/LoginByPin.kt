package com.example.meshmessenger.android.uicompose.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meshmessenger.android.theme.Poppins
import com.example.meshmessenger.android.theme.PrimaryColor
import com.example.meshmessenger.onboarding.LoginVM
import com.linecorp.abc.sharedstorage.SharedStorage
import dev.icerock.moko.mvvm.flow.compose.observeAsActions

@Composable
fun LoginByPin(viewModel: LoginVM = viewModel(), loginSuccess: () -> Unit   ) {

    val textOfState by viewModel.textState.collectAsState()

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
            .fillMaxWidth()) {

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

    Keyboard(viewModel)
}
