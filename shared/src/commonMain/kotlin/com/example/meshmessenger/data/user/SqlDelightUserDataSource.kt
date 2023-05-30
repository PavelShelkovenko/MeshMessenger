package com.example.meshmessenger.data.user

import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.domain.user.User
import com.example.meshmessenger.domain.user.UserDataSource

class SqlDelightUserDataSource(db: AppDatabase): UserDataSource {

    private val queries = db.appDatabaseQueries

    override suspend fun getAllUsers(): List<User> {
        return queries
            .getAllUsers()
            .executeAsList()
            .map { it.toUser() }
    }

    override suspend fun getUserById(id: String): User? {
        return queries
            .getUserById(id)
            .executeAsOneOrNull()
            ?.toUser()
    }

    override suspend fun insertUser(user: User) {
        queries.insertUser(
            id_peer = user.idPeer,
            name = user.name,
            surname = user.surname,
            is_active = user.isActive
        )
    }

    override suspend fun deleteUserById(id: String) {
        queries.deleteUserById(id)
    }
}