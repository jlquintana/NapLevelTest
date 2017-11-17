package com.joseluisquintana.napptilustest.app.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.joseluisquintana.data.OompaLoompa.OompaLoompa
import com.joseluisquintana.napptilustest.app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_oompa_loompa.view.*

class OompaLoompaListAdapter(val listener: OompaLoompaListAdapter.ItemClickListener):
        RecyclerView.Adapter<OompaLoompaListAdapter.RowViewHolder>() {

    private var oompaLoompas: MutableList<OompaLoompa>? = null

    fun updateList(oompaLoompas: List<OompaLoompa>) {
        val oldListSize = this.oompaLoompas?.size ?: 0
        val newListSize = oompaLoompas.size
        val rowsInserted = newListSize - oldListSize

        this.oompaLoompas = ArrayList<OompaLoompa>(oompaLoompas)

        notifyItemRangeInserted(oldListSize, rowsInserted)
    }

    fun clearList() {
        oompaLoompas?.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RowViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return RowViewHolder(layoutInflater.inflate(R.layout.row_oompa_loompa, parent, false), listener)
    }

    override fun onBindViewHolder(holder: RowViewHolder?, position: Int) {
        holder?.bind(oompaLoompas?.get(position))
    }

    override fun getItemCount(): Int {
        return this.oompaLoompas?.size ?: 0
    }

    class RowViewHolder(val view: View, val listener: OompaLoompaListAdapter.ItemClickListener): RecyclerView.ViewHolder(view) {

        fun bind(oompaLoompa: OompaLoompa?) {
            val context = view.context
            Picasso.with(context)
                    .load(oompaLoompa?.image)
                    .placeholder(R.drawable.no_avatar)
                    .error(R.drawable.no_avatar)
                    .into(view.imageIV)

            view.nameTV.text = oompaLoompa?.firstName + " " + oompaLoompa?.lastName
            view.professionTV.text = oompaLoompa?.profession
            view.emailTV.text = oompaLoompa?.email

            if (oompaLoompa?.gender != null && oompaLoompa.gender.equals("M")) {
                view.genderTV.text = context.getText(R.string.male)
            } else if (oompaLoompa?.gender != null && oompaLoompa.gender.equals("F")) {
                view.genderTV.text = context.getText(R.string.female)
            } else {
                view.genderTV.text = context.getText(R.string.unknown)
            }

            view.setOnClickListener {
                if (oompaLoompa != null) {
                    listener.onOompaLoompaClicked(oompaLoompa, view.imageIV)
                }
            }
        }
    }

    interface ItemClickListener {
        fun onOompaLoompaClicked(oompaLoompa: OompaLoompa, transitionImageView: ImageView)
    }
}
