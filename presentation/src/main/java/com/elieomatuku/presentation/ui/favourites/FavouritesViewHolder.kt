package com.elieomatuku.presentation.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.R
import kotlinx.android.synthetic.main.viewholder_searchresult.view.*

/**
 * Created by elieomatuku on 2021-06-18
 */

class FavouritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        private fun createView(parent: ViewGroup): View {
            return LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_favourite, parent, false)
        }

        fun newInstance(parent: ViewGroup): FavouritesViewHolder {
            return FavouritesViewHolder(createView(parent))
        }
    }

    private val locationTv: TextView by lazy {
        itemView.locationTv
    }

    fun update(location: Location) {
        locationTv.text = location.name
    }
}
