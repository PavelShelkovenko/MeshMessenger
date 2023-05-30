package com.example.meshmessenger.domain.user

data class User(
    val idPeer: String,
    val name: String,
    val surname: String,
    val isActive: Boolean
)
