package com.joseluisquintana.napptilustest.app.application.di

import android.app.Application
import android.content.Context
import com.joseluisquintana.napptilustest.app.application.MyApplication
import dagger.Module
import dagger.Provides

@ApplicationScope
@Module
class AppModule(private val app: MyApplication) {

    @Provides
    internal fun provideApplication(): Application {
        return app
    }

    @Provides
    internal fun provideApplicationContext(): Context {
        return app
    }
}
