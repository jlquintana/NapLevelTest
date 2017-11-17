package com.joseluisquintana.napptilustest.app.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.napptilustest.app.R
import com.joseluisquintana.napptilustest.app.application.MyApplication
import com.joseluisquintana.napptilustest.app.detail.di.OompaLoompaDetailComponent
import com.joseluisquintana.napptilustest.app.detail.di.OompaLoompaDetailModule
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_oompa_loompa_detail.*
import javax.inject.Inject

class OompaLoompaDetailActivity : AppCompatActivity(), OompaLoompaDetailPresenter.View {

    private val Activity.app: MyApplication get() = application as MyApplication

    @Inject
    lateinit var presenter: OompaLoompaDetailPresenter
    lateinit var oompaLoompaDetailComponent: OompaLoompaDetailComponent

    companion object {

        private val ARG_OOMPA = "arg_oompa"

        fun newIntent(context: Context, oompaLoompa: OompaLoompa): Intent {
            val intent = Intent(context, OompaLoompaDetailActivity::class.java)
            intent.putExtra(ARG_OOMPA, oompaLoompa)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oompa_loompa_detail)
        supportPostponeEnterTransition();

        oompaLoompaDetailComponent = lastCustomNonConfigurationInstance as OompaLoompaDetailComponent?
                ?: app.appComponent.plus(OompaLoompaDetailModule(intent.getParcelableExtra<OompaLoompa?>(ARG_OOMPA)))

        oompaLoompaDetailComponent.inject(this)
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
        return oompaLoompaDetailComponent
    }

    override fun showError() {
        Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
    }

    override fun showOompaLoompa(oompaLoompa: OompaLoompa?) {
        Picasso.with(this)
                .load(oompaLoompa?.image)
                .placeholder(R.drawable.no_avatar)
                .error(R.drawable.no_avatar)
                .into(imageIV, object : Callback {
                    override fun onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    override fun onError() {
                        supportStartPostponedEnterTransition();
                    }
                });

        nameTV.text = oompaLoompa?.firstName + " " + oompaLoompa?.lastName
        professionTV.text = oompaLoompa?.profession
        emailTV.text = oompaLoompa?.email
        descriptionTV.text = oompaLoompa?.description

        if (oompaLoompa?.gender != null && oompaLoompa.gender.equals("M")) {
            genderTV.text = getText(R.string.male)
        } else if (oompaLoompa?.gender != null && oompaLoompa.gender.equals("F")) {
            genderTV.text = getText(R.string.female)
        } else {
            genderTV.text = getText(R.string.unknown)
        }
    }
}
