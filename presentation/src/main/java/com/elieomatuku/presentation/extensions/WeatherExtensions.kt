package com.elieomatuku.presentation.extensions

import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.model.WeatherCondition
import com.elieomatuku.presentation.R

/**
 * Created by elieomatuku on 2021-06-17
 */

fun Weather.getBackgroundResources(): Int {
    return when (weatherCondition) {
        WeatherCondition.Sunny -> R.mipmap.forest_sunny
        WeatherCondition.Cloudy -> R.mipmap.forest_cloudy
        WeatherCondition.Rainy -> R.mipmap.forest_rainy
        else -> R.color.teal_700
    }
}

fun Weather.getSmallIconResources(): Int {
    return when (weatherCondition) {
        WeatherCondition.Sunny -> R.mipmap.clear
        WeatherCondition.Cloudy -> R.mipmap.partlysunny
        WeatherCondition.Rainy -> R.mipmap.rain
        else -> R.mipmap.clear
    }
}

fun Weather.descriptionResources(): Int? {
    return when (weatherCondition) {
        WeatherCondition.Sunny -> R.string.sunny
        WeatherCondition.Cloudy -> R.string.cloudy
        WeatherCondition.Rainy -> R.string.rainy
        WeatherCondition.Unknown -> null
    }
}

fun Weather.getBackgroundColorResources(): Int {
    return when (weatherCondition) {
        WeatherCondition.Sunny -> R.color.sunny
        WeatherCondition.Cloudy -> R.color.cloudy
        WeatherCondition.Rainy -> R.color.rainy
        else -> R.color.teal_700
    }
}
