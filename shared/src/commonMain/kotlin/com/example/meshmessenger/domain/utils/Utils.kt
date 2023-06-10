package com.example.meshmessenger.domain.utils

import com.linecorp.abc.sharedstorage.SharedStorage
import kotlinx.datetime.Clock
import kotlin.math.abs

fun startDestinationDefine(): String {
    val password = SharedStorage.secureLoad("password", "")
    val login = SharedStorage.secureLoad("login", "")

    val startDestination = if (password != "" && login != "") {
        "pin"
    } else {
        "register"
    }
    return startDestination
}

fun saveTime() {
    val currentMoment = Clock.System.now().epochSeconds.toInt()
    SharedStorage.save(currentMoment, "timeOfLastExitFromApp")
}

fun isTimeOut(): Boolean {
    val time = SharedStorage.load("timeOfLastExitFromApp", Clock.System.now().epochSeconds.toInt())
    if (abs(time + 5) < Clock.System.now().epochSeconds) {
        return true
    }
    return false
}