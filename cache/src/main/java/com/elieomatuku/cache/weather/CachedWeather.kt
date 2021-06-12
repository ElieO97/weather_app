package com.elieomatuku.cache.weather

import androidx.room.Entity

/**
 * Created by elieomatuku on 2021-06-12
 */

@Entity
data class CachedWeather(
    val temperature: Double,
    val minimumTemperature: Double,
    val weatherConditionId: Long,
    val weatherConditionDescription: String,
    val weatherConditionMain: String,
    val locationIdd: Long,
    val locationName: String
)
