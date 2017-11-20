package com.joseluisquintana.napptilustest.app.list

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.util.Log
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.domain.Filter
import com.joseluisquintana.domain.FilterOompaLoompasUseCase
import com.joseluisquintana.domain.GetOompaLoompaListUseCase
import com.joseluisquintana.napptilustest.app.detail.OompaLoompaDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OompaLoompaListPresenter(val getOompaLoompaListUseCase: GetOompaLoompaListUseCase,
                               val filterOompaLoompasUseCase: FilterOompaLoompasUseCase) {

    val TAG = "OompaLoompaListPresente"
    val oompaLoompas: ArrayList<OompaLoompa> = ArrayList<OompaLoompa>()

    var currentPage: Int = 1;
    var view: View? = null
    var gettingOompaLoompas = false
    var filter: Filter = Filter(true, true, true, true, true, true, true)

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

        // We do not change thread because the cost of changing thread is higher than the cost of filter computation.
        filterOompaLoompasUseCase.execute(oompaLoompas, filter)
                .subscribe(
                        {
                            view?.hideLoading()
                            view?.showOompaLoompas(it)
                        },
                        {
                            Log.e(TAG, "Error: ", it)
                            showError()
                        }

                )
    }

    fun onFilter(filter: Filter) {
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
