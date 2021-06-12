package com.elieomatuku.remote.model

import androidx.annotation.Keep

/**
 * Created by elieomatuku on 2021-06-12
 */

@Keep
data class RemoteWeather(
    val weather: WeatherCondition?,
    val main: WeatherMain?,
    val dt: Long?,
    val id: Long?,
    val name: String?,
    val coord: Coordinates?,
)
