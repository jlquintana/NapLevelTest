package com.joseluisquintana.napptilustest.app.detail.di

import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.data.OompaLoompa.OompaLoompaRepository
import com.joseluisquintana.domain.GetOompaLoompaDetailUseCase
import com.joseluisquintana.napptilustest.app.detail.OompaLoompaDetailPresenter
import dagger.Module
import dagger.Provides

@OompaLoompaDetailScope
@Module
class OompaLoompaDetailModule(val oompaLoompa: OompaLoompa?) {

    @OompaLoompaDetailScope
    @Provides
    internal fun providesGetOompaLoompaDetailUseCase(repository: OompaLoompaRepository): GetOompaLoompaDetailUseCase {
        return GetOompaLoompaDetailUseCase(repository)
    }

    @OompaLoompaDetailScope
    @Provides
    internal fun providesOompaLoompaDetailPresenter(getOompaLoompaDetailUseCase: GetOompaLoompaDetailUseCase):
            OompaLoompaDetailPresenter {
        return OompaLoompaDetailPresenter(oompaLoompa, getOompaLoompaDetailUseCase)
    }
}
