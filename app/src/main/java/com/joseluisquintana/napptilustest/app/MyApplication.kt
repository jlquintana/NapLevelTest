package com.joseluisquintana.napptilustest.app

import android.app.Application
import com.joseluisquintana.napptilustest.app.di.AppComponent
import com.joseluisquintana.napptilustest.app.di.AppModule
import com.joseluisquintana.napptilustest.app.di.DaggerAppComponent

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

    fun appComponent() : AppComponent = appComponent
}
