package com.joseluisquintana.napptilustest.app.application.di

import com.joseluisquintana.napptilustest.app.list.di.OompaLoompaListComponent
import com.joseluisquintana.napptilustest.app.list.di.OompaLoompaListModule
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent {
    fun plus(oompaLoompaListModule: OompaLoompaListModule): OompaLoompaListComponent
}
