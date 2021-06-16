package com.elieomatuku.presentation.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.model.WeatherCondition
import com.elieomatuku.presentation.R
import kotlinx.android.synthetic.main.viewholder_forecast.view.*
import kotlin.math.roundToInt

/**
 * Created by elieomatuku on 2021-06-17
 */

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        private fun createView(parent: ViewGroup): View {
            return LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_forecast, parent, false)
        }

        fun newInstance(parent: ViewGroup): ForecastViewHolder {
            return ForecastViewHolder(createView(parent))
        }
    }

    private val weekDayTv: TextView by lazy {
        itemView.weekDayTv
    }

    private val maxTempTv: TextView by lazy {
        itemView.maxTv
    }

    private val weatherConditionImageView: ImageView by lazy {
        itemView.weatherConditionImageView
    }

    fun update(weather: Weather) {
        weekDayTv.text = weather.weekDay
        maxTempTv.text = "${weather.temperature.roundToInt()}\u00B0"

        val imageRes = when (weather.weatherCondition) {
            WeatherCondition.Sunny -> R.mipmap.clear
            WeatherCondition.Cloudy -> R.mipmap.partlysunny
            WeatherCondition.Rainy -> R.mipmap.rain
            else -> R.mipmap.clear
        }
        weatherConditionImageView.setImageResource(imageRes)
    }
}
