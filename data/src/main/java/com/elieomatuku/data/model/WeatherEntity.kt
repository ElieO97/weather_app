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
                weatherConditionMain = weatherEntity.weatherConditionEntity.main,
                weatherConditionId = weatherEntity.weatherConditionEntity.id,
                weatherConditionDescription = weatherEntity.weatherConditionEntity.description,
                lastUpdate = weatherEntity.lastUpdatedInMilliseconds
            )
        }

        fun fromWeather(weather: Weather): WeatherEntity {
            return WeatherEntity(
                temperature = weather.temperature,
                minimumTemperature = weather.minimumTemperature,
                maximumTemperature = weather.maximumTemperature,
                location = weather.location.let(LocationEntity::fromLocation),
                date = weather.date,
                weekDay = weather.weekDay,
                weatherConditionEntity = WeatherConditionEntity(
                    id = weather.weatherConditionId,
                    main = weather.weatherConditionMain ?: "",
                    description = weather.weatherConditionDescription
                ),
                lastUpdatedInMilliseconds = weather.lastUpdate
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
