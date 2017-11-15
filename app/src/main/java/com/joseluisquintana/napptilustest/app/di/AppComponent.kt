package com.joseluisquintana.napptilustest.app.di

import com.joseluisquintana.napptilustest.app.di.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
}
