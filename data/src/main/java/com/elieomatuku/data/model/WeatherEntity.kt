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
) {

    companion object {
        fun toWeather(weatherEntity: WeatherEntity): Weather {
            return Weather(
                temperature = weatherEntity.temperature,
                minimumTemperature = weatherEntity.minimumTemperature,
                maximumTemperature = weatherEntity.maximumTemperature,
                location = weatherEntity.location.let(LocationEntity::toLocation),
                date = weatherEntity.date,
                weatherCondition = weatherEntity.weatherConditionEntity.let(WeatherConditionEntity::toWeatherCondition),
                lastUpdate = weatherEntity.lastUpdatedInMilliseconds
            )
        }
    }
}
