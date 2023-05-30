package com.example.meshmessenger.data.user

import com.example.meshmessenger.domain.user.User
import database.UserEntity

fun UserEntity.toUser(): User {
    return User(
        idPeer = id_peer,
        name = name,
        surname = surname,
        isActive = is_active
    )
}