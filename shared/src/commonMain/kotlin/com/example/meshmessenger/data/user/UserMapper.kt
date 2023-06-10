package com.example.meshmessenger.data.user

import com.example.meshmessenger.domain.user.User
import database.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id_peer = id_peer,
        name = name,
        surname = surname,
        user_image = user_image,
        is_active = is_active
    )
}