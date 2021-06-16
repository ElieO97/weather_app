package com.elieomatuku.presentation.ui.weather

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Weather

/**
 * Created by elieomatuku on 2021-06-17
 */

class ForecastAdapter(private val forecast: List<Weather>) :
    RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecast[position]
        holder.update(forecast)
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
}
