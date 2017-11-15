package com.joseluisquintana.domain

import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.data.OompaLoompa.OompaLoompaRepository
import io.reactivex.Single

class GetOompaLoompaDetailUseCase(val repository: OompaLoompaRepository) {

    fun execute(id: Long): Single<OompaLoompa> {
        return repository.getOompaLoompa(id)
    }
}
