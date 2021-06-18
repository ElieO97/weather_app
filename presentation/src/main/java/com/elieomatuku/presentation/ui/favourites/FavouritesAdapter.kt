package com.elieomatuku.presentation.ui.favourites

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Location
import io.reactivex.subjects.PublishSubject

/**
 * Created by elieomatuku on 2021-06-18
 */

class FavouritesAdapter(
    private val favourites: List<Location>,
    private val addLocationPublisher: PublishSubject<Location>
) :
    RecyclerView.Adapter<FavouritesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val location = favourites[position]
        holder.update(location)
        holder.itemView.setOnClickListener {
            addLocationPublisher.onNext(location)
        }
    }

    override fun getItemCount(): Int {
        return favourites.size
    }
}
