package com.joseluisquintana.napptilustest.app.list.di

import com.joseluisquintana.napptilustest.app.list.OompaLoompaListActivity
import dagger.Subcomponent


@OompaLoompaListScope
@Subcomponent(modules = arrayOf(OompaLoompaListModule::class))
interface OompaLoompaListComponent {

    fun inject(activity: OompaLoompaListActivity)
}
