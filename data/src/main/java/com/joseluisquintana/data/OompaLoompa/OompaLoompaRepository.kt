package com.joseluisquintana.data.OompaLoompa

import io.reactivex.Single

class OompaLoompaRepository(val externalDataSource: OompaLoompaDataSource,
                            val cacheOompaLoompaDataSource: CacheOompaLoompaDataSource) {

    fun getOompaLoompas(page: Int): Single<List<OompaLoompa>> {
        return this.cacheOompaLoompaDataSource.getOompaLoompas(page)
                .onErrorResumeNext(
                        externalDataSource.getOompaLoompas(page)
                            .doOnSuccess { cacheOompaLoompaDataSource.updateOompaLoompaList(page, it) }
                )
    }

    fun getOompaLoompa(id: Long): Single<OompaLoompa> {
        return this.cacheOompaLoompaDataSource.getOompaLoompa(id)
                .onErrorResumeNext(
                        externalDataSource.getOompaLoompa(id)
                            // Oompa Loompa models that come from backend has no ID, so we must add the id to the model
                            // in order to store it into cache
                            .map { it.id = id; it }
                            .doOnSuccess { cacheOompaLoompaDataSource.updateOompaLoompa(it) }
                )
    }
}
