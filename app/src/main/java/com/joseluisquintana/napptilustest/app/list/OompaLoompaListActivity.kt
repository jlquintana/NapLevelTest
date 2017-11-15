package com.joseluisquintana.napptilustest.app.list

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.napptilustest.app.MyApplication
import com.joseluisquintana.napptilustest.app.R
import javax.inject.Inject

class OompaLoompaListActivity : AppCompatActivity(), OompaLoompaListPresenter.View {

    val Activity.app: MyApplication get() = application as MyApplication

    @Inject lateinit var presenter: OompaLoompaListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oompa_loompa_list)

        app.appComponent
                .inject(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.onViewReady(this)
    }

    override fun onPause() {
        super.onPause()

        presenter.onViewDestroyed()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showOompaLoompas(oompaLoompas: ArrayList<OompaLoompa>) {

    }

    override fun showError() {

    }

}
