package com.joseluisquintana.data.OompaLoompa

import io.reactivex.Single

class OompaLoompaRepository(val externalDataSource: OompaLoompaDataSource,
                            val cacheOompaLoompaDataSource: CacheOompaLoompaDataSource) {

    fun getOompaLoompas(page: Int): Single<List<OompaLoompa>> {
        return this.cacheOompaLoompaDataSource.getOompaLoompas(page)
                .doOnSuccess { oompaLoompas -> cacheOompaLoompaDataSource.updateOompaLoompaList(page, oompaLoompas) }
                .onErrorResumeNext(externalDataSource.getOompaLoompas(page))
    }

    fun getOompaLoompa(id: Long): Single<OompaLoompa> {
        return this.cacheOompaLoompaDataSource.getOompaLoompa(id)
                .doOnSuccess { oompaLoompa -> cacheOompaLoompaDataSource.updateOompaLoompa(oompaLoompa) }
                .onErrorResumeNext(externalDataSource.getOompaLoompa(id))
    }
}
