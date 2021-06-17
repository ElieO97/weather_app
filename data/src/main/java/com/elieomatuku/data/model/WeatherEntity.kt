package com.elieomatuku.data.model

import com.elieomatuku.domain.model.Weather

/**
 * Created by elieomatuku on 2021-06-12
 */

data class WeatherEntity(
    val temperature: Double,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val location: LocationEntity,
    val date: Long,
    val weatherConditionEntity: WeatherConditionEntity,
    val lastUpdatedInMilliseconds: Long,
    val weekDay: String? = null
) {

    companion object {
        fun toWeather(weatherEntity: WeatherEntity): Weather {
            return Weather(
                temperature = weatherEntity.temperature,
                minimumTemperature = weatherEntity.minimumTemperature,
                maximumTemperature = weatherEntity.maximumTemperature,
                location = weatherEntity.location.let(LocationEntity::toLocation),
                date = weatherEntity.date,
                weekDay = weatherEntity.weekDay,
                weatherCondition = weatherEntity.weatherConditionEntity.let(WeatherConditionEntity::toWeatherCondition),
                lastUpdate = weatherEntity.lastUpdatedInMilliseconds
            )
        }

        fun toWeatherList(weatherEntities: List<WeatherEntity>): List<Weather> {
            return weatherEntities
                .map {
                    val weather: Weather = toWeather(it)
                    weather
                }
        }
    }

    fun updateLocation(lat: Double, long: Double): WeatherEntity {
        val location = location.copy(latitude = lat, longitude = long)
        return copy(location = location)
    }
}
