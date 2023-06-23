package com.example.meshmessenger.di

import com.example.meshmessenger.Strings
import com.example.meshmessenger.database.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            AppDatabase.Schema,
            get(),
            "appDatabase.db"
        )
    }
    single { Strings(get()) }
}