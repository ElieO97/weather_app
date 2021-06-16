package com.elieomatuku.cache.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.model.WeatherConditionEntity
import com.elieomatuku.data.model.WeatherEntity

/**
 * Created by elieomatuku on 2021-06-12
 */

@Entity(tableName = CachedWeather.WEATHER_TABLE)
data class CachedWeather(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val temperature: Double,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val weatherConditionId: Int,
    val weatherConditionDescription: String,
    val weatherConditionMain: String,
    val locationName: String,
    val locationLongitude: Double,
    val locationLatitude: Double,
    val currentWeather: Boolean? = null,
    val date: Long,
    val lastUpdateInMilliseconds: Long
) {
    companion object {
        const val WEATHER_TABLE = "weather_table"

        fun toWeatherEntity(cacheWeather: CachedWeather): WeatherEntity {
            return WeatherEntity(
                temperature = cacheWeather.temperature,
                minimumTemperature = cacheWeather.minimumTemperature,
                maximumTemperature = cacheWeather.maximumTemperature,
                location = LocationEntity(
                    name = cacheWeather.locationName,
                    longitude = cacheWeather.locationLongitude,
                    latitude = cacheWeather.locationLatitude,
                ),
                date = cacheWeather.date,
                weatherConditionEntity = WeatherConditionEntity(
                    cacheWeather.weatherConditionId,
                    cacheWeather.weatherConditionMain,
                    cacheWeather.weatherConditionDescription
                ),
                lastUpdatedInMilliseconds = cacheWeather.lastUpdateInMilliseconds
            )
        }

        fun fromWeatherEntity(
            weatherEntity: WeatherEntity,
            currentWeather: Boolean? = null
        ): CachedWeather {
            return CachedWeather(
                temperature = weatherEntity.temperature,
                minimumTemperature = weatherEntity.minimumTemperature,
                maximumTemperature = weatherEntity.maximumTemperature,
                locationName = weatherEntity.location.name,
                locationLongitude = weatherEntity.location.longitude,
                locationLatitude = weatherEntity.location.latitude,
                date = weatherEntity.date,
                weatherConditionId = weatherEntity.weatherConditionEntity.id,
                weatherConditionMain = weatherEntity.weatherConditionEntity.main,
                weatherConditionDescription = weatherEntity.weatherConditionEntity.description,
                lastUpdateInMilliseconds = weatherEntity.lastUpdatedInMilliseconds,
                currentWeather = currentWeather
            )
        }
    }
}
