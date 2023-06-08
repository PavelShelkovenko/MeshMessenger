package com.example.meshmessenger.android.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.meshmessenger.android.presentation.theme.BackgroundColor
import com.example.meshmessenger.android.presentation.theme.White
import com.juul.kable.*
import com.juul.kable.logs.Hex
import com.juul.kable.logs.Logging
import com.juul.kable.logs.SystemLogEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

data class MyAdvertisement(
    val address: String,
)

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("MutableCollectionMutableState", "UnrememberedMutableState")
@Composable
fun BleUI() {


    val textExample = remember { mutableStateOf("Здесь мы должны получит информацию") }
    val bytesArray = remember { mutableStateOf(byteArrayOf()) }

    val listOfMyAdvertisements: MutableList<MyAdvertisement> = mutableListOf() //для проверки пришел ли новый адрес
    val listOfAdvertisements: MutableList<Advertisement> = mutableListOf()
    val wrapListOfAdvertisements = remember { mutableStateOf(listOfAdvertisements) }
    val scope = CoroutineScope(Dispatchers.IO)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            val scanner = Scanner {
                filters = null

                logging {
                    engine = SystemLogEngine
                    level = Logging.Level.Warnings
                    format = Logging.Format.Multiline
                    data = Logging.DataProcessor {
                        bytesArray.value.joinToString { byte -> byte.toString() }
                    }
                }
            }

            scope.launch {
                scanner.advertisements
                    .filter { (it.isConnectable == true) }
                    .collect {
                        println(" ------------${it?.name}--- ${it.address} ${it.rssi} ${it.peripheralName}  ")
                        val tmp = MyAdvertisement(address = it.address)
                        if (tmp !in listOfMyAdvertisements) {
                            listOfMyAdvertisements.add(tmp)
                            val list = ArrayList(wrapListOfAdvertisements.value)
                            list.add(it)
                            wrapListOfAdvertisements.value  = list
                        }

                        val peripheral = scope.peripheral(it) {
                            onServicesDiscovered {

                            }
                            transport = Transport.Le // default
                            phy = Phy.Le1M // default
                        }

                        val characteristic = characteristicOf(
                            service = "00001815-0000-1000-8000-00805f9b34fb",
                            characteristic = "00002a56-0000-1000-8000-00805f9b34fb",
                        )

                        val observation = peripheral.observe(characteristic)
                        observation.collect { data ->
                            println("!!!!!!!!$data")

                        }
                    }
            }
        }
        ) {
            Text("Scan")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(textExample.value)
        Spacer(modifier = Modifier.height(5.dp))
        Text( bytesArray.value.toString() )

        Spacer(modifier = Modifier.height(50.dp))

        LazyColumn {
            items(wrapListOfAdvertisements.value) { advertisement ->
                BluetoothNodeUICard(advertisement)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}


@Composable
fun BluetoothNodeUICard(advertisement: Advertisement) {

    val scope = CoroutineScope(Dispatchers.IO)
    val focus = LocalFocusManager.current

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
                        scope.peripheral(advertisement) {
                                logging {
                                    engine = SystemLogEngine
                                    format = Logging.Format.Multiline
                                    data = Hex
                                    level = Logging.Level.Data
                                }

                                transport = Transport.Le
                                phy = Phy.Le1M
                            }
                            .connect()
                    }
                }
        ) {
            Text(advertisement.address)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                Text("${advertisement.name.toString()}  ${advertisement.peripheralName} ")
                Button(onClick = {
                    scope.launch {
                        scope.peripheral(advertisement).write( descriptor, byteArrayOf(1,2,3) )
                        scope.peripheral(advertisement).read(descriptor)
                    }
                } ){
                    Text("send ")
                }
            }
        }
    }
}

val descriptor = descriptorOf(
    service = "00001815-0000-1000-8000-00805f9b34fb",
    characteristic = "00002a56-0000-1000-8000-00805f9b34fb",
    descriptor = "00002902-0000-1000-8000-00805f9b34fb"
)
@SuppressLint("MissingPermission")
fun Activity.enableBluetooth() {
    startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), RequestCode.EnableBluetooth)
}

object RequestCode {
    const val EnableBluetooth = 55001
}