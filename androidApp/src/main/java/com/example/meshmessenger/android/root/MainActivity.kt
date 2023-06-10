package com.example.meshmessenger.android.root

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.MeshAppTheme
import com.example.meshmessenger.android.screens.*
import com.example.meshmessenger.android.screens.chats.ChatsListScreen
import com.example.meshmessenger.android.screens.messages.MessagesListScreen
import com.example.meshmessenger.android.screens.onboarding.Registration
import com.example.meshmessenger.android.screens.onboarding.login.LoginByPin
import com.example.meshmessenger.presentation.chat.ChatViewModel
import com.example.meshmessenger.presentation.message.MessageViewModel
import com.example.meshmessenger.presentation.onboarding.LoginViewModel
import com.example.meshmessenger.presentation.onboarding.RegistrationViewModel
import com.linecorp.abc.sharedstorage.SharedStorage
import kotlinx.datetime.Clock
import org.koin.androidx.compose.koinViewModel
import kotlin.math.abs

class MainActivity : ComponentActivity() {

    private lateinit var sharedPrefs: SharedPreferences
    private var startDestination: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = this.getSharedPreferences("currenttime", MODE_PRIVATE)
        val pickMultiMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uri -> }

        setContent {
            startDestination = startDestinationDefine()

            MeshAppTheme {
                Surface(color = BackgroundColor, modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    Root(
                        startDestination = startDestination,
                        onStart = { isTimeOut() },
                        onStop = { saveTime() },
                        callPin = {
                            navController.navigate("pin") {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        },
                        callRegister = {
                            navController.navigate("register") {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        },
                        navController = navController,
                        pickMultiMedia = pickMultiMedia,
                    )
                }
            }
        }
    }

    private fun startDestinationDefine(): String {
        val password = SharedStorage.secureLoad("password", "")
        val login = SharedStorage.secureLoad("login", "")

        val startDestination = if (password != "" && login != "") {
            "pin"
        } else {
            "register"
        }
        return startDestination
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
    registrationViewModel: RegistrationViewModel= koinViewModel(),
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

fun saveTime() {
    val currentMoment = Clock.System.now().epochSeconds.toInt()
    SharedStorage.save(currentMoment, "timeOfLastExitFromApp")
}

fun isTimeOut(): Boolean {
    val time = SharedStorage.load("timeOfLastExitFromApp", Clock.System.now().epochSeconds.toInt())
    if (abs(time + 50) < Clock.System.now().epochSeconds) {
        return true
    }
    return false
}



