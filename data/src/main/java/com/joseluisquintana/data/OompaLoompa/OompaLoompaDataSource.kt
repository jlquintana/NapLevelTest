package com.joseluisquintana.data.OompaLoompa

import io.reactivex.Single

interface OompaLoompaDataSource {

    fun getOompaLoompas(page: Int) : Single<List<OompaLoompa>>
    fun getOompaLoompa(id: Long) : Single<OompaLoompa>
}
