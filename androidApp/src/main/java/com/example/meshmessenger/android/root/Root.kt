package com.example.meshmessenger.android.root

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.meshmessenger.android.screens.BleUI
import com.example.meshmessenger.android.screens.chats.ChatsListScreen
import com.example.meshmessenger.android.screens.messages.MessagesListScreen
import com.example.meshmessenger.android.screens.onboarding.registration.Registration
import com.example.meshmessenger.android.screens.onboarding.login.LoginByPin
import com.example.meshmessenger.domain.utils.DateTimeUtil
import com.example.meshmessenger.presentation.chat.ChatViewModel
import com.example.meshmessenger.presentation.message.MessageViewModel
import com.example.meshmessenger.presentation.onboarding.LoginViewModel
import com.example.meshmessenger.presentation.onboarding.RegistrationViewModel
import com.linecorp.abc.sharedstorage.SharedStorage
import kotlinx.datetime.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun Root(
    startDestination: String,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Boolean,
    onStop: () -> Unit,
    callPin: () -> Unit,
    callRegister: () -> Unit,
    navController: NavHostController,
    pickMultiMedia: ActivityResultLauncher<PickVisualMediaRequest>,
    registrationViewModel: RegistrationViewModel = koinViewModel(),
    loginViewModel: LoginViewModel = koinViewModel(),
    chatViewModel: ChatViewModel = koinViewModel(),
    messageViewModel: MessageViewModel = koinViewModel()
) {
    val saveTime by rememberUpdatedState(onStart)
    val pullOutTime by rememberUpdatedState(onStop)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {

                val pswValue: String = SharedStorage.secureLoad("login", "")
                val loginValue: String = SharedStorage.secureLoad("password", "")
                if (pswValue == "" || loginValue == "") {
                    callRegister()
                } else if ( saveTime() ) {
                    callPin()
                }
            } else if (event == Lifecycle.Event.ON_STOP) {
                pullOutTime()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    NavHost(navController, startDestination) {
        composable("register") {
            Registration(
                registrationViewModel = registrationViewModel,
                onLoginSuccess = {
                    navController.navigate("pin") {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("pin") {
            LoginByPin(
                loginViewModel = loginViewModel,
                loginSuccess = {
                    navController.navigate("chatListScreen") {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("chatListScreen") {
            ChatsListScreen(
                navController,
                chatViewModel = chatViewModel
            )
        }
        composable(
            route = "messagesList/{chatName}",
            arguments = listOf(
                navArgument("chatName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            MessagesListScreen(
                navController,
                backStackEntry.arguments?.getString("chatName"),
                pickMultiMedia,
                messageViewModel = messageViewModel
            )
        }
        composable("ble") {
            BleUI()
        }
    }
}