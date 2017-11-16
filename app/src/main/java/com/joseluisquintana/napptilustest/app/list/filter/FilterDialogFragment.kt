package com.joseluisquintana.napptilustest.app.list.filter

import android.app.DialogFragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joseluisquintana.napptilustest.app.R

class FilterDialogFragment : DialogFragment() {

    private var mListener: OnFilterListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter_dialog, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.filter()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFilterListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFilterListener {
        fun filter()
    }
}
