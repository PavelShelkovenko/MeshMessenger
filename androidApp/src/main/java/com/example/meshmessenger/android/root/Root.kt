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
import com.example.meshmessenger.AndroidChatViewModel
import com.example.meshmessenger.AndroidRegistrationViewModel
import com.example.meshmessenger.android.screens.BleUI
import com.example.meshmessenger.android.screens.channels.ChannelsListScreen
import com.example.meshmessenger.android.screens.messages.MessagesListScreen
import com.example.meshmessenger.android.screens.onboarding.registration.Registration
import com.example.meshmessenger.android.screens.onboarding.login.LoginByPin
import com.example.meshmessenger.presentation.channelScreen.ChannelViewModel
import com.liftric.kvault.KVault
import org.koin.androidx.compose.get
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
    registrationViewModel: AndroidRegistrationViewModel = koinViewModel(),
    chatViewModel: ChannelViewModel = koinViewModel(),
    messageViewModel: AndroidChatViewModel = koinViewModel(),
//    bluetoothManager: BluetoothManager,
//    bluetoothAdapter: BluetoothAdapter,
//    bluetoothLeAdvertiser: BluetoothLeAdvertiser,
) {
    val saveTime by rememberUpdatedState(onStart)
    val pullOutTime by rememberUpdatedState(onStop)
    val secureStore: KVault = get()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                val loginValue: String = secureStore.string(forKey = "login") ?: ""
                val pswValue: String = secureStore.string(forKey = "password") ?: ""
                if (loginValue == "" || pswValue == "") {
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
                onAccountCreated = {
                    navController.navigate("pin") {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("pin") {
            LoginByPin(
                loginSuccess = {
                    navController.navigate("channelsListScreen") {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("channelsListScreen") {
            ChannelsListScreen(
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
                chatViewModel = messageViewModel
            )
        }
        composable("ble") {
            BleUI(
//                bluetoothManager = bluetoothManager,
//                bluetoothLeAdvertiser = bluetoothLeAdvertiser,
//                bluetoothAdapter = bluetoothAdapter
            )
        }
    }
}