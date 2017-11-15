package com.joseluisquintana.domain

import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.data.OompaLoompa.OompaLoompaRepository
import io.reactivex.Single

class GetOompaLoompaListUseCase(val repository: OompaLoompaRepository) {

    fun execute(page: Int): Single<List<OompaLoompa>> {
        return repository.getOompaLoompas(page)
    }
}
