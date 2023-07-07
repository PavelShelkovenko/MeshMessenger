package com.example.meshmessenger.domain.utils

import com.liftric.kvault.KVault
import kotlinx.datetime.Clock
import kotlin.math.abs

fun startDestinationDefine(secureStore: KVault): String {
    val password = secureStore.string("password")
    val login = secureStore.string("login")

    val startDestination = if (password != "" && login != "") {
        "pin"
    } else {
        "register"
    }
    println(startDestination)
    return startDestination
}

fun saveTime(secureStore: KVault) {
    val currentMoment = Clock.System.now().epochSeconds.toInt()
    secureStore.set("timeOfLastExitFromApp", currentMoment)
}

fun isTimeOut(secureStore: KVault): Boolean {
    val time = secureStore.int("timeOfLastExitFromApp")
    if (time != null) {
        if (abs(time + 5) < Clock.System.now().epochSeconds) {
            return true
        }
    }
    return false
}