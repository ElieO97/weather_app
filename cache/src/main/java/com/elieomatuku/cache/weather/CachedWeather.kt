package com.elieomatuku.cache.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by elieomatuku on 2021-06-12
 */

@Entity(tableName = CachedWeather.WEATHER_TABLE)
data class CachedWeather(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val temperature: Double,
    val minimumTemperature: Double,
    val weatherConditionId: Long,
    val weatherConditionDescription: String,
    val weatherConditionMain: String,
    val locationIdd: Long,
    val locationName: String,
    val lastUpdateInMilliseconds: Long
) {
    companion object {
        const val WEATHER_TABLE = "weather_table"
    }
}
