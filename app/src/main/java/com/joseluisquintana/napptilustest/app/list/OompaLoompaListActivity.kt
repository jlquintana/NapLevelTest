package com.joseluisquintana.napptilustest.app.list

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.domain.Filter
import com.joseluisquintana.napptilustest.app.R
import com.joseluisquintana.napptilustest.app.application.MyApplication
import com.joseluisquintana.napptilustest.app.list.di.OompaLoompaListComponent
import com.joseluisquintana.napptilustest.app.list.di.OompaLoompaListModule
import com.joseluisquintana.napptilustest.app.list.filter.FilterDialogFragment
import kotlinx.android.synthetic.main.activity_oompa_loompa_list.*
import javax.inject.Inject


class OompaLoompaListActivity : AppCompatActivity(), OompaLoompaListPresenter.View,
        OompaLoompaListAdapter.ItemClickListener, FilterDialogFragment.OnFilterListener {

    private val Activity.app: MyApplication get() = application as MyApplication

    private val BOTTOM_SCROLL_THRESHOLD = 3
    private val oompaLoompaListAdapter = OompaLoompaListAdapter(this)
    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    @Inject
    lateinit var presenter: OompaLoompaListPresenter
    lateinit var oompaLoompaListComponent: OompaLoompaListComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oompa_loompa_list)

        listRV.layoutManager = linearLayoutManager
        listRV.adapter = oompaLoompaListAdapter
        listRV.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                //if near third item from end
                val lastItemVisible = linearLayoutManager.childCount + linearLayoutManager.findFirstVisibleItemPosition()
                if (lastItemVisible >= linearLayoutManager.itemCount - BOTTOM_SCROLL_THRESHOLD) {
                    presenter.onListAtBottom()
                }
            }
        })

        oompaLoompaListComponent = lastCustomNonConfigurationInstance as OompaLoompaListComponent?
                ?: app.appComponent.plus(OompaLoompaListModule())

        oompaLoompaListComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.onViewReady(this)
    }

    override fun onPause() {
        super.onPause()

        presenter.onViewDestroyed()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return oompaLoompaListComponent
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.filter) {
            val fm = fragmentManager
            val dialogFragment = FilterDialogFragment()
            dialogFragment.show(fm, "FilterDialogFragment")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onOompaLoompaClicked(oompaLoompa: OompaLoompa, transitionImageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, transitionImageView, ViewCompat
                .getTransitionName(transitionImageView))
        presenter.onOompaLoompaClicked(this, oompaLoompa, options)
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    override fun showOompaLoompas(oompaLoompas: List<OompaLoompa>) {
        oompaLoompaListAdapter.updateList(oompaLoompas)
    }

    override fun removeOompaLoompaList() {
        oompaLoompaListAdapter.clearList()
    }

    override fun showError() {
        Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
    }

    override fun filter(filter: Filter) {
        presenter.onFilter(filter)
    }
}

