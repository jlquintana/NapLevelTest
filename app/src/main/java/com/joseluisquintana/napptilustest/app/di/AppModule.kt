package com.joseluisquintana.napptilustest.app.di

import android.app.Application
import android.content.Context
import com.joseluisquintana.napptilustest.app.MyApplication
import com.joseluisquintana.napptilustest.app.di.scopes.ApplicationScope
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
