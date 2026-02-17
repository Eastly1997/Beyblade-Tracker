package com.lkby.tracker

import android.app.Application
import com.google.firebase.FirebaseApp
import com.lkby.tracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}