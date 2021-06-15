package com.elieomatuku.presentation.ui.weather

import com.elieomatuku.domain.model.Weather

/**
 * Created by elieomatuku on 2021-06-14
 */

data class WeatherViewState(
    val isLoading: Boolean = false,
    val weather: Weather? = null
)
