package com.joseluisquintana.napptilustest.app.di

import com.joseluisquintana.napptilustest.app.di.scopes.OompaLoompaListScope
import com.joseluisquintana.napptilustest.app.list.OompaLoompaListActivity
import dagger.Subcomponent


@OompaLoompaListScope
@Subcomponent(modules = arrayOf(OompaLoompaListModule::class))
interface OompaLoompaListComponent {

    fun inject(activity: OompaLoompaListActivity)
}
