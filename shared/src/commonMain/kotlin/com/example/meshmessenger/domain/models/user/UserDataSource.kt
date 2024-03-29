package com.example.meshmessenger.domain.models.user

interface UserDataSource {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(id: String): User?
    suspend fun insertUser(user: User)
    suspend fun deleteUserById(id: String)
}