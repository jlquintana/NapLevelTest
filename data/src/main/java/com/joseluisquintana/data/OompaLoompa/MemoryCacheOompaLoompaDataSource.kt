package com.joseluisquintana.data.OompaLoompa

import io.reactivex.Single

class MemoryCacheOompaLoompaDataSource: CacheOompaLoompaDataSource {

    private val oompaLoompasDetail by lazy {
        HashMap<Long, OompaLoompa>()
    }

    private val oompaLoompasList by lazy {
        HashMap<Int, List<OompaLoompa>>()
    }

    override fun getOompaLoompas(page: Int): Single<List<OompaLoompa>> {
        if (!this.oompaLoompasList.containsKey(page)) {
            return Single.error<List<OompaLoompa>>(NoSuchElementException())
        }

        return Single.just(this.oompaLoompasList.get(page))
    }

    override fun updateOompaLoompaList(page: Int, oompaLoompas: List<OompaLoompa>) {
        oompaLoompas.forEach {
            oompaLoompa -> this.oompaLoompasList.put(page, oompaLoompas)
        }
    }

    override fun getOompaLoompa(id: Long): Single<OompaLoompa> {
        if (!this.oompaLoompasDetail.contains(id)) {
            return Single.error<OompaLoompa>(NoSuchElementException())
        }

        return Single.just(this.oompaLoompasDetail.get(id))
    }

    override fun updateOompaLoompa(oompaLoompa: OompaLoompa) {
        this.oompaLoompasDetail.put(oompaLoompa.id, oompaLoompa)
    }
}
