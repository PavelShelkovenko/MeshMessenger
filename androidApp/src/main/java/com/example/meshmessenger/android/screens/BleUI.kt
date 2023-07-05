package com.example.meshmessenger.android.screens

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.bluetooth.le.BluetoothLeAdvertiser
import android.content.Context
import android.os.ParcelUuid
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
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.White
import com.juul.kable.*
import com.juul.kable.logs.Logging
import com.juul.kable.logs.SystemLogEngine
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*

@SuppressLint("MutableCollectionMutableState")
@Composable
fun BleUI(
    bluetoothAdapter: BluetoothAdapter? = null,
    bluetoothManager: BluetoothManager? = null,
    bluetoothLeAdvertiser: BluetoothLeAdvertiser? = null
) {

    val textExample = remember { mutableStateOf("Здесь мы должны получить строку") }
    val listOfAdvertisements: MutableList<AndroidAdvertisement> = mutableListOf()
    val wrapListOfAdvertisements = remember { mutableStateOf(listOfAdvertisements) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            Button(onClick = { CoroutineScope(Dispatchers.IO).launch {

                val begin = System.nanoTime()
                try {
                    kableScan(wrapListOfAdvertisements)

                } catch (e : GattStatusException){
                    val end = System.nanoTime()
                    println("Elapsed time in nanoseconds: ${end-begin}")

                } catch (e: Exception){ kableScan(wrapListOfAdvertisements) }

            } })
            { Text("Scan Kable") }

            Spacer(modifier = Modifier.width(5.dp))

            Button(onClick = {  }) { Text("Stop scan Kable") }
        }

        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            Button(onClick = {  startAdvertising(bluetoothLeAdvertiser!!, bluetoothManager!!, bluetoothAdapter!!, context )  }) { Text("Advertising Start") }

            Spacer(modifier = Modifier.width(5.dp))

            Button(onClick = { stopAdvertising(bluetoothLeAdvertiser!!) }) { Text("Stop Advertising") }
        }

        Text(textExample.value)
        Spacer(modifier = Modifier.height(5.dp))
        Spacer(modifier = Modifier.height(5.dp))

        LazyColumn {
            items(wrapListOfAdvertisements.value) { advertisement ->
                BluetoothNodeUICard(advertisement, textExample)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

fun stopAdvertising(bluetoothLeAdvertiser: BluetoothLeAdvertiser) {
    gattServer?.close()
    gattServer = null
    bluetoothLeAdvertiser.stopAdvertising(advertiseCallback)

}

private var gattServer: BluetoothGattServer? = null
private val subscribedDevices = mutableSetOf<BluetoothDevice>()


private val gattServerCallback = object : BluetoothGattServerCallback() {
    override fun onConnectionStateChange(device: BluetoothDevice, status: Int, newState: Int) {
        if (newState == BluetoothProfile.STATE_CONNECTED) {
            Log.e(TAG, "connect")
        } else {
            Log.e(TAG, "disconnect")
        }
    }

    override fun onNotificationSent(device: BluetoothDevice, status: Int) {
        Log.e(TAG, "onNotificationSent status=$status")
    }

    override fun onCharacteristicReadRequest(device: BluetoothDevice, requestId: Int, offset: Int, characteristic: BluetoothGattCharacteristic) {
        var log = "onCharacteristicRead offset=$offset"
        if (characteristic.uuid == UUID.fromString(CHAR_FOR_READ_UUID)) {
                val strValue = "helloWorld"
                gattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, 0, strValue.toByteArray(Charsets.UTF_8))
                Log.e(TAG, log)

        } else {
            gattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_FAILURE, 0, null)
            Log.e(TAG, log)
        }
    }

    override fun onCharacteristicWriteRequest(device: BluetoothDevice, requestId: Int, characteristic: BluetoothGattCharacteristic, preparedWrite: Boolean, responseNeeded: Boolean, offset: Int, value: ByteArray?) {
        var log = "onCharacteristicWrite offset=$offset responseNeeded=$responseNeeded preparedWrite=$preparedWrite"
        if (characteristic.uuid == UUID.fromString(CHAR_FOR_WRITE_UUID)) {
            val strValue = value?.toString(Charsets.UTF_8) ?: ""
            if (responseNeeded) {
                gattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, 0, strValue.toByteArray(Charsets.UTF_8))
                Log.e(TAG, "\nresponse=success, value=\"$strValue\"")
            } else {
                Log.e(TAG, "\nresponse=notNeeded, value=\"$strValue\"")
            }

            //вывод на экран

        } else {
            if (responseNeeded) {
                gattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_FAILURE, 0, null)
                Log.e(TAG, "\nresponse=failure, unknown UUID\n${characteristic.uuid}")
            } else {
                Log.e(TAG, "\nresponse=notNeeded, unknown UUID\n${characteristic.uuid}")
            }
        }
    }

    override fun onDescriptorReadRequest(device: BluetoothDevice, requestId: Int, offset: Int, descriptor: BluetoothGattDescriptor) {
        if (descriptor.uuid == UUID.fromString(CCC_DESCRIPTOR_UUID)) {
            val returnValue = if (subscribedDevices.contains(device)) {
                Log.e(TAG, " CCCD response=ENABLE_NOTIFICATION")
                BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            } else {
                Log.e(TAG, " CCCD response=DISABLE_NOTIFICATION")
                BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
            }
            gattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, 0, returnValue)
        } else {
            Log.e(TAG, " unknown uuid=${descriptor.uuid}")
            gattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_FAILURE, 0, null)
        }
    }

    override fun onDescriptorWriteRequest(device: BluetoothDevice, requestId: Int, descriptor: BluetoothGattDescriptor, preparedWrite: Boolean, responseNeeded: Boolean, offset: Int, value: ByteArray) {
        var strLog = "onDescriptorWriteRequest"
        if (descriptor.uuid == UUID.fromString(CCC_DESCRIPTOR_UUID)) {
            var status = BluetoothGatt.GATT_REQUEST_NOT_SUPPORTED
            if (descriptor.characteristic.uuid == UUID.fromString(CHAR_FOR_INDICATE_UUID)) {
                if (Arrays.equals(value, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE)) {
                    subscribedDevices.add(device)
                    status = BluetoothGatt.GATT_SUCCESS
                    strLog += ", subscribed"
                } else if (Arrays.equals(value, BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)) {
                    subscribedDevices.remove(device)
                    status = BluetoothGatt.GATT_SUCCESS
                    strLog += ", unsubscribed"
                }
            }
            if (responseNeeded) {
                gattServer?.sendResponse(device, requestId, status, 0, null)
            }
        } else {
            strLog += " unknown uuid=${descriptor.uuid}"
            if (responseNeeded) {
                gattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_FAILURE, 0, null)
            }
        }
    }
}

private val advertiseSettings = AdvertiseSettings.Builder()
    .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED)
    .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
    .setConnectable(true)
    .build()



private val advertiseCallback = object : AdvertiseCallback() {
    override fun onStartSuccess(settingsInEffect: AdvertiseSettings) {
        Log.e(TAG, "Advertise start success\n$SERVICE_UUID")
    }

    override fun onStartFailure(errorCode: Int) {
        val desc = when (errorCode) {
            ADVERTISE_FAILED_DATA_TOO_LARGE -> "\nADVERTISE_FAILED_DATA_TOO_LARGE"
            ADVERTISE_FAILED_TOO_MANY_ADVERTISERS -> "\nADVERTISE_FAILED_TOO_MANY_ADVERTISERS"
            ADVERTISE_FAILED_ALREADY_STARTED -> "\nADVERTISE_FAILED_ALREADY_STARTED"
            ADVERTISE_FAILED_INTERNAL_ERROR -> "\nADVERTISE_FAILED_INTERNAL_ERROR"
            ADVERTISE_FAILED_FEATURE_UNSUPPORTED -> "\nADVERTISE_FAILED_FEATURE_UNSUPPORTED"
            else -> ""
        }
        Log.e(TAG, "Advertise start failed: errorCode=$errorCode $desc")
    }
}

fun startAdvertising(bluetoothLeAdvertiser: BluetoothLeAdvertiser, bluetoothManager: BluetoothManager, bluetoothAdapter: BluetoothAdapter, context: Context) {

    val gattServer = bluetoothManager.openGattServer(context, gattServerCallback)
    val service = BluetoothGattService(UUID.fromString(SERVICE_UUID), BluetoothGattService.SERVICE_TYPE_PRIMARY)
    var charForRead = BluetoothGattCharacteristic(UUID.fromString(CHAR_FOR_READ_UUID),
        BluetoothGattCharacteristic.PROPERTY_READ,
        BluetoothGattCharacteristic.PERMISSION_READ)
    var charForWrite = BluetoothGattCharacteristic(UUID.fromString(CHAR_FOR_WRITE_UUID),
        BluetoothGattCharacteristic.PROPERTY_WRITE,
        BluetoothGattCharacteristic.PERMISSION_WRITE)
    var charForIndicate = BluetoothGattCharacteristic(UUID.fromString(CHAR_FOR_INDICATE_UUID),
        BluetoothGattCharacteristic.PROPERTY_INDICATE,
        BluetoothGattCharacteristic.PERMISSION_READ)
    var charConfigDescriptor = BluetoothGattDescriptor(UUID.fromString(CCC_DESCRIPTOR_UUID),
        BluetoothGattDescriptor.PERMISSION_READ or BluetoothGattDescriptor.PERMISSION_WRITE)
    charForIndicate.addDescriptor(charConfigDescriptor)

    service.addCharacteristic(charForRead)
    service.addCharacteristic(charForWrite)
    service.addCharacteristic(charForIndicate)

    val result = gattServer.addService(service)
    com.example.meshmessenger.android.screens.gattServer = gattServer
    bluetoothLeAdvertiser.startAdvertising(advertiseSettings, advertiseData, advertiseCallback)
    Log.e(TAG, result.toString())

}


suspend fun kableScan(wrapListOfAdvertisements: MutableState<MutableList<AndroidAdvertisement>>) {

    val advertisement = Scanner {
        filters = listOf(Filter.Service(uuidFrom("25AE1441-05D3-4C5B-8281-93D4E07420CF")))
        logging {
            engine = SystemLogEngine
            level = Logging.Level.Data
            format = Logging.Format.Multiline
        }
    }.advertisements.first()

    val list = ArrayList(wrapListOfAdvertisements.value)
    list.add(advertisement)
    wrapListOfAdvertisements.value = list

    yield()

}


@Composable
fun BluetoothNodeUICard(advertisement: AndroidAdvertisement, textExample: MutableState<String>) {

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
                        scope.peripheral(advertisement) {
                            onServicesDiscovered {
                                val bytes = this.read(characteristicRead)
                                this.write(characteristicWrite, bytes, WriteType.WithResponse )
                                textExample.value = String(bytes)
                            }
                        }.connect()
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

private val advertiseData = AdvertiseData.Builder()
    .setIncludeDeviceName(false) // don't include name, because if name size > 8 bytes, ADVERTISE_FAILED_DATA_TOO_LARGE
    .addServiceUuid(ParcelUuid(UUID.fromString("25AE1441-05D3-4C5B-8281-93D4E07420CF")))
    .build()

private const val SERVICE_UUID = "25AE1441-05D3-4C5B-8281-93D4E07420CF"
private const val CHAR_FOR_READ_UUID = "25AE1442-05D3-4C5B-8281-93D4E07420CF"
private const val CHAR_FOR_WRITE_UUID = "25AE1443-05D3-4C5B-8281-93D4E07420CF"
private const val CHAR_FOR_INDICATE_UUID = "25AE1444-05D3-4C5B-8281-93D4E07420CF"
private const val CCC_DESCRIPTOR_UUID = "00002902-0000-1000-8000-00805f9b34fb"
const val TAG = "logggg"