package com.joseluisquintana.napptilustest.app.list.di

import com.joseluisquintana.data.OompaLoompa.OompaLoompaRepository
import com.joseluisquintana.domain.GetOompaLoompaListUseCase
import com.joseluisquintana.napptilustest.app.list.OompaLoompaListPresenter
import dagger.Module
import dagger.Provides

@OompaLoompaListScope
@Module
class OompaLoompaListModule {

    @OompaLoompaListScope
    @Provides
    internal fun providesGetOompaLoompaListUseCase(repository: OompaLoompaRepository): GetOompaLoompaListUseCase {
        return GetOompaLoompaListUseCase(repository)
    }

    @OompaLoompaListScope
    @Provides
    internal fun providesOompaLoompaListPresenter(getOompaLoompaListUseCase: GetOompaLoompaListUseCase): OompaLoompaListPresenter {
        return OompaLoompaListPresenter(getOompaLoompaListUseCase)
    }
}
