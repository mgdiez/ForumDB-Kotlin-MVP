package com.marcgdiez.jsonplaceholder

import android.app.Application
import com.marcgdiez.jsonplaceholder.di.appModule
import org.koin.android.ext.android.startKoin

class JsonApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            appModule
        ))
    }
}