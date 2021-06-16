package com.elieomatuku.domain.model

/**
 * Created by elieomatuku on 2021-06-12
 */

data class Weather(
    val temperature: Double,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val weatherCondition: WeatherCondition,
    val location: Location,
    val date: Long,
    val weekDay: String? = null,
    val lastUpdate: Long,
)
