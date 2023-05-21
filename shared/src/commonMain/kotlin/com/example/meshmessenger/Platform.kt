package com.example.meshmessenger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform