package com.example.meshmessenger

import com.example.meshmessenger.database.UserDatabase

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}