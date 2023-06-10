package com.example.meshmessenger.domain.user

data class User(
    val id_peer: String,
    val name: String,
    val surname: String,
    val user_image: String,
    val is_active: Boolean
)
