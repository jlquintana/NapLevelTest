package com.joseluisquintana.napptilustest.app.detail

import android.util.Log
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.domain.GetOompaLoompaDetailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OompaLoompaDetailPresenter(val oompaLoompaPreview: OompaLoompa?, val getOompaLoompaDetailUseCase:
GetOompaLoompaDetailUseCase) {

    val TAG = "OompaLoompaDetailPresenter"
    var oompaLoompa: OompaLoompa? = null;
    var view: View? = null

    init {
        getOompaLoompaDetail()
    }

    fun onViewReady(view: View) {
        this.view = view
        showOompaLoompaDetail()
    }

    fun onViewDestroyed() {
        this.view = null
    }

    private fun getOompaLoompaDetail() {
        if (oompaLoompaPreview == null) {
            view?.showError()
            return
        }

        view?.showLoading()
        getOompaLoompaDetailUseCase.execute(oompaLoompaPreview.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            oompaLoompa = it
                            showOompaLoompaDetail()
                        },
                        {
                            Log.e(TAG, "Error: ", it)
                            showError()
                        }
                )
    }

    private fun showError() {
        view?.hideLoading()
        view?.showError()
    }

    private fun showOompaLoompaDetail() {
        view?.hideLoading()
        view?.showOompaLoompa(oompaLoompa ?: oompaLoompaPreview)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun showOompaLoompa(oompaLoompa: OompaLoompa?)
    }
}
