package com.joseluisquintana.napptilustest.app.list

import android.util.Log
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.domain.GetOompaLoompaListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OompaLoompaListPresenter(val getOompaLoompaListUseCase: GetOompaLoompaListUseCase) {

    val TAG = "OompaLoompaListPresente"
    val oompaLoompas: ArrayList<OompaLoompa> = ArrayList<OompaLoompa>()

    var currentPage: Int = 0;
    var view: View? = null
    var gettingOompaLoompas = false

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

    private fun getNextOompaLoompasPage() {
        if (gettingOompaLoompas) {
            return
        }

        gettingOompaLoompas = true
        view?.showLoading()
        currentPage++
        getOompaLoompaListUseCase.execute(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
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
        view?.showOompaLoompas(oompaLoompas)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showOompaLoompas(oompaLoompas: ArrayList<OompaLoompa>)
        fun showError()
    }
}
