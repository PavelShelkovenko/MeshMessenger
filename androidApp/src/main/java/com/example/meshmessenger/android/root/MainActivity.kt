package com.example.meshmessenger.android.root

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.meshmessenger.android.uicompose.ChannelListScreen
import com.example.meshmessenger.android.uicompose.DialogMessagesList
import com.example.meshmessenger.android.uicompose.Registration
import com.example.meshmessenger.android.uicompose.loginScreen.LoginByPin
import com.example.meshmessenger.data.channelsListExample
import com.linecorp.abc.sharedstorage.SharedStorage
import kotlinx.datetime.Clock
import kotlin.math.abs

class MainActivity : ComponentActivity() {

    private lateinit var sharedPrefs: SharedPreferences
    private var startDestination: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = this.getSharedPreferences("currenttime", MODE_PRIVATE)
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
                        navController = navController
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

@Composable
fun Root(
    startDestination: String,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Boolean,
    onStop: () -> Unit,
    callPin: () -> Unit,
    callRegister: () -> Unit,
    navController: NavHostController
) {
    val saveTime by rememberUpdatedState(onStart)
    val pullOutTime by rememberUpdatedState(onStop)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if ( event == Lifecycle.Event.ON_START) {
                val pswValue: String = SharedStorage.secureLoad("login", "")
                val loginValue: String = SharedStorage.secureLoad("password", "")
                if(pswValue == "" || loginValue == ""){
                    callRegister()
                } else if (saveTime()) {
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
                loginSuccess = {
                    navController.navigate("channelListScreen") {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("channelListScreen") {
            ChannelListScreen(navController, channelsListExample)
        }
        composable(
            route = "messagesList/{channelID}/{channelName}",
            arguments = listOf(
                navArgument("channelID") { type = NavType.IntType },
                navArgument("channelName") { type = NavType.StringType}
            )) {
                backStackEntry ->
            DialogMessagesList(
                navController,
                backStackEntry.arguments?.getInt("channelID"),
                backStackEntry.arguments?.getString("channelName")
            )

        }
    }
}

fun saveTime() {
    val currentMoment = Clock.System.now().epochSeconds.toInt()
    SharedStorage.save(currentMoment, "timeOfLastExitFromApp")
}

fun isTimeOut(): Boolean {
    val time = SharedStorage.load("timeOfLastExitFromApp", Clock.System.now().epochSeconds.toInt())
    if ( abs(time + 5) < Clock.System.now().epochSeconds) {
        return true
    }
    return false
}



