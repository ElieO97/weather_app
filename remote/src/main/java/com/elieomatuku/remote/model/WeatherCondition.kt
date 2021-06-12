package com.elieomatuku.remote.model

import androidx.annotation.Keep

/**
 * Created by elieomatuku on 2021-06-12
 */

@Keep
data class WeatherCondition(
    val id: Long?,
    val main: String?,
    val description: String?
)
