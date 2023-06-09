package com.example.meshmessenger.di

import com.example.meshmessenger.AppInfo
import com.example.meshmessenger.database.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.KoinApplication
import org.koin.dsl.module


fun initKoinIos(
    appInfo: AppInfo,
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    module {
        single { appInfo }
        single { doOnStartup }
    }
)

actual val platformModule = module {

    single<SqlDriver> { NativeSqliteDriver(AppDatabase.Schema, "appDatabase.db") }

}