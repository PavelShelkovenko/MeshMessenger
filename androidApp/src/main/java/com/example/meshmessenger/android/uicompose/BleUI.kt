package com.example.meshmessenger.android.uicompose

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.White
import com.juul.kable.*
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
@SuppressLint("MutableCollectionMutableState")
@Composable
fun BleUI() {

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

                    level = Logging.Level.Data
                    format = Logging.Format.Multiline
                    data = Logging.DataProcessor { bytes ->
                        bytes.joinToString { byte -> byte.toString() } // Show data as integer representation of bytes.
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
                    }
            }

        }
        ) {
            Text("Scan")
        }

        Spacer(modifier = Modifier.height(50.dp))

        LazyColumn() {
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
                        scope.peripheral(advertisement){

                                transport = Transport.Le
                                phy = Phy.Le1M

                            }.connect()
                    }
                }
        ) {
            Text(advertisement.address)
            Spacer(modifier = Modifier.height(10.dp))
            Text("${advertisement.name.toString()}  ${advertisement.peripheralName} ")
        }
    }
}