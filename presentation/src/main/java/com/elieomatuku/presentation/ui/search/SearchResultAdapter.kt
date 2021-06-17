package com.elieomatuku.presentation.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Location

/**
 * Created by elieomatuku on 2021-06-18
 */

class SearchResultAdapter(private val results: List<Location>) :
    RecyclerView.Adapter<SearchResultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val location = results[position]
        holder.update(location)
    }

    override fun getItemCount(): Int {
        return results.size
    }
}
