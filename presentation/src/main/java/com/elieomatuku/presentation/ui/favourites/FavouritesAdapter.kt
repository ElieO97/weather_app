package com.elieomatuku.presentation.ui.favourites

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Location
import io.reactivex.subjects.PublishSubject

/**
 * Created by elieomatuku on 2021-06-18
 */

class FavouritesAdapter(
    private val favourites: MutableList<Location>,
    private val locationSelectionPublisher: PublishSubject<Location>,
    private val locationDeletionPublisher: PublishSubject<Location>
) :
    RecyclerView.Adapter<FavouritesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val location = favourites[position]
        holder.update(location)
        holder.itemView.setOnClickListener {
            locationSelectionPublisher.onNext(location)
        }
    }

    override fun getItemCount(): Int {
        return favourites.size
    }

    fun deleteFavourite(position: Int) {
        val location = favourites[position]
        locationDeletionPublisher.onNext(location)
        favourites.removeAt(position)
        notifyItemRemoved(position)
    }
}
