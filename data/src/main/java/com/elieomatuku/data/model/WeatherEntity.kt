package com.elieomatuku.data.model

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
    val lastUpdate: Long,
)
