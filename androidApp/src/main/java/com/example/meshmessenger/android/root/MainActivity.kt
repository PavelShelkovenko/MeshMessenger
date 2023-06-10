package com.example.meshmessenger.android.root

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.meshmessenger.android.screens.*
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.MeshAppTheme
import com.example.meshmessenger.domain.utils.isTimeOut
import com.example.meshmessenger.domain.utils.saveTime
import com.example.meshmessenger.domain.utils.startDestinationDefine


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
}






