package com.joseluisquintana.napptilustest.app.di

import com.joseluisquintana.napptilustest.app.di.scopes.ApplicationScope
import com.joseluisquintana.napptilustest.app.list.OompaLoompaListActivity
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(AppModule::class, DataModule::class, OompaLoompaListModule::class))
interface AppComponent {

    fun inject(activity: OompaLoompaListActivity)
}
