package com.example.meshmessenger.android.root

import android.app.Application
import com.example.meshmessenger.platformModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application()  {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)

            androidLogger()
            modules(androidModule + platformModule())
        }
    }
}