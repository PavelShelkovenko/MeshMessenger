package com.example.meshmessenger.android.root

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.example.meshmessenger.resources.Strings
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.MeshAppTheme
import com.example.meshmessenger.domain.utils.isTimeOut
import com.example.meshmessenger.domain.utils.saveTime
import com.example.meshmessenger.domain.utils.startDestinationDefine
import com.liftric.kvault.KVault
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.FontResource
import dev.icerock.moko.resources.StringResource
import org.koin.androidx.compose.get
import androidx.compose.ui.text.font.FontFamily


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val bluetoothManager: BluetoothManager by lazy {
//            getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
//        }
//        val bluetoothAdapter: BluetoothAdapter by lazy { bluetoothManager.adapter }
//        val bleAdvertiser by lazy { bluetoothAdapter.bluetoothLeAdvertiser }
//
        val pickMultiMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uri -> }


        setContent {
            MeshAppTheme {
                Surface(color = BackgroundColor, modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val secureStore: KVault = get()
                    Root(
                        startDestination = startDestinationDefine(secureStore = secureStore),
                        onStart = { isTimeOut(secureStore = secureStore) },
                        onStop = { saveTime(secureStore = secureStore) },
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
//                        bluetoothAdapter = bluetoothAdapter,
//                        bluetoothLeAdvertiser = bleAdvertiser,
//                        bluetoothManager = bluetoothManager
                    )
                }
            }
        }
    }
}

@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return Strings(LocalContext.current).get(id, args.toList())
}

@Composable
fun colorResource(resource: ColorResource): Color {
    val context: Context = LocalContext.current
    return Color(resource.getColor(context))
}

@Composable
fun fontFamilyResource(fontResource: FontResource): FontFamily {
    return fontResource.asFont()
        ?.let { FontFamily(it) }
        ?:  FontFamily.Default
}

@Composable
fun FontResource.asFont(
    weight: FontWeight = FontWeight.Normal,
    style: FontStyle = FontStyle.Normal,
): Font? = remember(fontResourceId) {
    Font(
        resId = fontResourceId,
        weight = weight,
        style = style,
    )
}







