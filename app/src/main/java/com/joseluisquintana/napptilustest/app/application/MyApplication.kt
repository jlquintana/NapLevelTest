package com.joseluisquintana.napptilustest.app.application

import android.app.Application
import com.joseluisquintana.napptilustest.app.application.di.AppComponent
import com.joseluisquintana.napptilustest.app.application.di.AppModule
import com.joseluisquintana.napptilustest.app.application.di.DaggerAppComponent

class MyApplication : Application() {


    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}
