package com.joseluisquintana.napptilustest.app.di

import com.joseluisquintana.napptilustest.app.di.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent {
    fun plus(oompaLoompaListModule: OompaLoompaListModule): OompaLoompaListComponent
}
