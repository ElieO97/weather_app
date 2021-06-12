package com.elieomatuku.remote.model

import androidx.annotation.Keep

/**
 * Created by elieomatuku on 2021-06-12
 */

@Keep
data class WeatherMain(
    val temp: Double?,
    val temp_min: Double?,
    val temp_max: Double?,
)
