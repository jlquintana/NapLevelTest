package com.joseluisquintana.napptilustest.app.list

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.util.Log
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.domain.GetOompaLoompaListUseCase
import com.joseluisquintana.napptilustest.app.detail.OompaLoompaDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OompaLoompaListPresenter(val getOompaLoompaListUseCase: GetOompaLoompaListUseCase) {

    val TAG = "OompaLoompaListPresente"
    val oompaLoompas: ArrayList<OompaLoompa> = ArrayList<OompaLoompa>()

    var currentPage: Int = 1;
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
        view?.showOompaLoompas(oompaLoompas)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showOompaLoompas(oompaLoompas: ArrayList<OompaLoompa>)
        fun showError()
    }
}
