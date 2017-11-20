package com.joseluisquintana.domain

import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import io.reactivex.Single

class FilterOompaLoompasUseCase {

    fun execute(oompaLoompas: List<OompaLoompa>, filter: Filter): Single<List<OompaLoompa>> {
        return Single.fromCallable {
            oompaLoompas.filter { shouldFilter(filter, it) }
        }
    }

    private fun shouldFilter(filter: Filter, oompaLoompa: OompaLoompa): Boolean {
        val genderFilterEmpty = !filter.male && !filter.female

        // Filter by gender only when there are one or more filtered options.
        if (!genderFilterEmpty) {
            if (!filter.male && oompaLoompa.gender?.equals("M") ?: false) {
                return false
            }
            if (!filter.female && oompaLoompa.gender?.equals("F") ?: false) {
                return false
            }
        }

        val professionFilterEmpty = !filter.developer
                && !filter.metalworker
                && !filter.brewer
                && !filter.gemcutter
                && !filter.medic

        // Filter by profession only when there are one or more filtered options.
        if (!professionFilterEmpty) {
            if (!filter.developer && oompaLoompa.profession?.equals("Developer") ?: false) {
                return false
            }
            if (!filter.metalworker && oompaLoompa.profession?.equals("Metalworker") ?: false) {
                return false
            }
            if (!filter.brewer && oompaLoompa.profession?.equals("Brewer") ?: false) {
                return false
            }
            if (!filter.gemcutter && oompaLoompa.profession?.equals("Gemcutter") ?: false) {
                return false
            }
            if (!filter.medic && oompaLoompa.profession?.equals("Medic") ?: false) {
                return false
            }
        }

        return true
    }
}
