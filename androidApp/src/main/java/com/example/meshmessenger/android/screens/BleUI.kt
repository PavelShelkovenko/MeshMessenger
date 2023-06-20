package com.example.meshmessenger.android.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.uuidFrom
import com.example.meshmessenger.android.root.Application
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.White
import com.juul.kable.*
import com.juul.kable.logs.Hex
import com.juul.kable.logs.Logging
import com.juul.kable.logs.SystemLogEngine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList



@SuppressLint("MutableCollectionMutableState")
@Composable
fun BleUI(application: Application) {

    val textExample = remember { mutableStateOf("Здесь мы должны получить строку") }
    val bytesArray = remember { mutableStateOf(byteArrayOf()) }
    val listOfAdvertisements: MutableList<AndroidAdvertisement> = mutableListOf()
    val wrapListOfAdvertisements = remember { mutableStateOf(listOfAdvertisements) }
    val scope = CoroutineScope(Dispatchers.IO)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row {
            Button(onClick = { kableScan(scope, bytesArray, wrapListOfAdvertisements) }) { Text("Scan Kable") }

            Spacer(modifier = Modifier.width(5.dp))

            Button(onClick = { scope.cancel() }) { Text("Stop scan Kable") }

            Spacer(modifier = Modifier.width(5.dp))

            Button(onClick = { scanProof(context) }) { Text("scan Proof ") }

            Spacer(modifier = Modifier.width(5.dp))

            Button(onClick = { }) { Text(" Stop scan Proof ") }

        }


        Spacer(modifier = Modifier.height(5.dp))
        Text(textExample.value)
        Spacer(modifier = Modifier.height(5.dp))
        Text(bytesArray.value.toString())
        Spacer(modifier = Modifier.height(50.dp))

        LazyColumn {
            items(wrapListOfAdvertisements.value) { advertisement ->
                BluetoothNodeUICard(advertisement)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

fun scanProof(context: Context) {}


fun kableScan(
    scope: CoroutineScope,
    bytesArray: MutableState<ByteArray>,
    wrapListOfAdvertisements: MutableState<MutableList<AndroidAdvertisement>>
) {

    val scanner = Scanner {

        filters = listOf(Filter.Service(uuidFrom("25AE1441-05D3-4C5B-8281-93D4E07420CF")))

        logging {
            engine = SystemLogEngine
            level = Logging.Level.Data
            format = Logging.Format.Multiline
            data = Logging.DataProcessor {
                bytesArray.value.joinToString { byte ->
                    byte.toString()
                }
            }
        }
    }

    scope.launch {
        scanner.advertisements
            .filter { true }
            .uniqueValuesFromFlow()
            .collect { advertisement ->

                val list = ArrayList(wrapListOfAdvertisements.value)
                list.add(advertisement)
                wrapListOfAdvertisements.value = list

            }
    }
}


@Composable
fun BluetoothNodeUICard(advertisement: AndroidAdvertisement) {

    val scope = CoroutineScope(Dispatchers.IO)

    Surface(elevation = 20.dp, shape = RoundedCornerShape(8.dp)) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth(0.95F)
                .background(White)
                .clickable {
                    scope.launch {

                        scope.peripheral(advertisement).connect()

                        delay(100)


                        scope.peripheral(advertisement) {
                            onServicesDiscovered {
                                scope.peripheral(advertisement).read(characteristicRead)
                                println(scope.peripheral(advertisement).read(characteristicRead))
                            }
                        }

                        scope.peripheral(advertisement).observe(characteristicRead).collect { data ->
//                            bytesArray.value = data
                            println("!!!!!!!!$data")
                        }

                        scope.peripheral(advertisement).state.collect { state ->
                            println("ssssssssstate $state")
                        }

                    }
                }

        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(advertisement.address)
                Spacer(modifier = Modifier.height(10.dp))
                Text("${advertisement.peripheralName} ")
                Button(onClick = {
                    scope.launch {
                        scope.peripheral(advertisement) {
                            Log.e(TAG, "send button pressed")
                            onServicesDiscovered {
                                scope.peripheral(advertisement).write(characteristicWrite, "hello world".toByteArray(), WriteType.WithResponse)
                            }
                        }
                    }
                }) {
                    Text("send")
                }
            }
        }
    }
}


val characteristicWrite = characteristicOf(
    service = "25AE1441-05D3-4C5B-8281-93D4E07420CF",
    characteristic = "25AE1443-05D3-4C5B-8281-93D4E07420CF",
)

val characteristicRead = characteristicOf(
    service = "25AE1441-05D3-4C5B-8281-93D4E07420CF",
    characteristic = "25AE1442-05D3-4C5B-8281-93D4E07420CF",
)

val descriptor = descriptorOf(
    service = "25AE1441-05D3-4C5B-8281-93D4E07420CF",
    characteristic = "25AE1443-05D3-4C5B-8281-93D4E07420CF",
    descriptor = "00002902-0000-1000-8000-00805f9b34fb"
)

@SuppressLint("MissingPermission")
fun Activity.enableBluetooth() {
    startActivityForResult(
        Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),
        RequestCode.EnableBluetooth
    )
}

object RequestCode {
    const val EnableBluetooth = 55001
}


fun Flow<AndroidAdvertisement>.uniqueValuesFromFlow(): Flow<AndroidAdvertisement> {
    val addresses = mutableListOf<String>()
    return flow {
        collect { androidAdvertisement ->
            println(" ----${androidAdvertisement.isConnectable}--------${androidAdvertisement.name}--- ${androidAdvertisement.address} ${androidAdvertisement.rssi} ${androidAdvertisement.peripheralName} ${androidAdvertisement.uuids} ${androidAdvertisement.txPower} ")

            if (androidAdvertisement.address !in addresses) {
                addresses.add(androidAdvertisement.address)
                emit(androidAdvertisement)
            }

        }
    }
}

private const val SERVICE_UUID = "25AE1441-05D3-4C5B-8281-93D4E07420CF"
private const val CHAR_FOR_READ_UUID = "25AE1442-05D3-4C5B-8281-93D4E07420CF"
private const val CHAR_FOR_WRITE_UUID = "25AE1443-05D3-4C5B-8281-93D4E07420CF"
private const val CHAR_FOR_INDICATE_UUID = "25AE1444-05D3-4C5B-8281-93D4E07420CF"
private const val CCC_DESCRIPTOR_UUID = "00002902-0000-1000-8000-00805f9b34fb"
const val TAG = "logggg"
