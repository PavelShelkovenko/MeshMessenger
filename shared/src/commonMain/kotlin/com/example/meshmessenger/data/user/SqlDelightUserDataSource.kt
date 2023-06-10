package com.example.meshmessenger.data.user

import com.example.meshmessenger.database.AppDatabase
import com.example.meshmessenger.domain.models.user.User
import com.example.meshmessenger.domain.models.user.UserDataSource
import com.squareup.sqldelight.db.SqlDriver

class SqlDelightUserDataSource(sqlDriver: SqlDriver): UserDataSource {

    private val db = AppDatabase(sqlDriver)
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
            id_peer = user.id_peer,
            name = user.name,
            surname = user.surname,
            is_active = user.is_active,
            user_image = user.user_image
        )
    }

    override suspend fun deleteUserById(id: String) {
        queries.deleteUserById(id)
    }
}