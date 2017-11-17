package com.joseluisquintana.napptilustest.app.detail.di

import com.joseluisquintana.napptilustest.app.detail.OompaLoompaDetailActivity
import dagger.Subcomponent

@OompaLoompaDetailScope
@Subcomponent(modules = arrayOf(OompaLoompaDetailModule::class))
interface OompaLoompaDetailComponent {

    fun inject(activity: OompaLoompaDetailActivity)
}
