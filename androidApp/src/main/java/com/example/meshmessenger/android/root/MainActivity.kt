package com.example.meshmessenger.android.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.MeshAppTheme
import com.example.meshmessenger.domain.utils.isTimeOut
import com.example.meshmessenger.domain.utils.saveTime
import com.example.meshmessenger.domain.utils.startDestinationDefine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pickMultiMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uri -> }

        setContent {
            MeshAppTheme {
                Surface(color = BackgroundColor, modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    Root(
                        startDestination = startDestinationDefine(),
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






