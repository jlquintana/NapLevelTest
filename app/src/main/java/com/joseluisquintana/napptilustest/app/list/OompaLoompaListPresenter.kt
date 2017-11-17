package com.joseluisquintana.napptilustest.app.list

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.util.Log
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.domain.GetOompaLoompaListUseCase
import com.joseluisquintana.napptilustest.app.detail.OompaLoompaDetailActivity
import com.joseluisquintana.napptilustest.app.list.filter.FilterModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OompaLoompaListPresenter(val getOompaLoompaListUseCase: GetOompaLoompaListUseCase) {

    val TAG = "OompaLoompaListPresente"
    val oompaLoompas: ArrayList<OompaLoompa> = ArrayList<OompaLoompa>()

    var currentPage: Int = 1;
    var view: View? = null
    var gettingOompaLoompas = false
    var filter: FilterModel = FilterModel(true, true, true, true, true, true, true)

    init {
        getNextOompaLoompasPage()
    }

    fun onViewReady(view: View) {
        this.view = view
        if (!oompaLoompas.isEmpty()) {
            showOompaLoompas()
        }
    }

    fun onViewDestroyed() {
        this.view = null
    }

    fun onListAtBottom() {
        getNextOompaLoompasPage()
    }

    fun onOompaLoompaClicked(context: Context, oompaLoompa: OompaLoompa, options: ActivityOptionsCompat) {
        val detailIntent = OompaLoompaDetailActivity.newIntent(context, oompaLoompa)
        context.startActivity(detailIntent, options.toBundle())
    }

    private fun getNextOompaLoompasPage() {
        if (gettingOompaLoompas) {
            return
        }

        gettingOompaLoompas = true
        view?.showLoading()
        getOompaLoompaListUseCase.execute(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            currentPage++
                            this.oompaLoompas.addAll(it)
                            showOompaLoompas()
                        },
                        {
                            Log.e(TAG, "Error: ", it)
                            showError()
                        }
                )
    }

    private fun showError() {
        gettingOompaLoompas = false
        view?.hideLoading()
        view?.showError()
    }

    private fun showOompaLoompas() {
        gettingOompaLoompas = false
        view?.hideLoading()
        view?.showOompaLoompas(oompaLoompas
                .filter { shouldFilter(it) })
    }

    private fun shouldFilter(it: OompaLoompa): Boolean {
        val genderFilterEmpty = !filter.male && !filter.female
        if (!genderFilterEmpty) {
            if (!filter.male && it.gender?.equals("M") ?: false) {
                return false
            }
            if (!filter.female && it.gender?.equals("F") ?: false) {
                return false
            }
        }

        val professionFilterEmpty = !filter.developer
                && !filter.metalworker
                && !filter.brewer
                && !filter.gemcutter
                && !filter.medic

        if (!professionFilterEmpty) {
            if (!filter.developer && it.profession?.equals("Developer") ?: false) {
                return false
            }
            if (!filter.metalworker && it.profession?.equals("Metalworker") ?: false) {
                return false
            }
            if (!filter.brewer && it.profession?.equals("Brewer") ?: false) {
                return false
            }
            if (!filter.gemcutter && it.profession?.equals("Gemcutter") ?: false) {
                return false
            }
            if (!filter.medic && it.profession?.equals("Medic") ?: false) {
                return false
            }
        }

        return true
    }

    fun onFilter(filter: FilterModel) {
        this.filter = filter
        view?.removeOompaLoompaList()
        showOompaLoompas()
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showOompaLoompas(oompaLoompas: List<OompaLoompa>)
        fun showError()
        fun removeOompaLoompaList()
    }
}
