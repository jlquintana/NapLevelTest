package com.joseluisquintana.data.OompaLoompa

interface CacheOompaLoompaDataSource: OompaLoompaDataSource {

    fun updateOompaLoompaList(page: Int, oompaLoompas: List<OompaLoompa>)
    fun updateOompaLoompa(oompaLoompa: OompaLoompa)
}
