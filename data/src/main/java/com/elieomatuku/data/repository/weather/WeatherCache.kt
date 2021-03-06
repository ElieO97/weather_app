package com.elieomatuku.data.repository.weather

import com.elieomatuku.data.model.WeatherEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface WeatherCache {

    fun clearAllWeather()

    fun clearWeatherForLocation(locationId: Long)

    fun isCached(lat: Double, long: Double): Boolean

    fun isExpired(lat: Double, long: Double): Boolean

    fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity?

    fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<WeatherEntity>

    fun saveWeather(weatherEntity: WeatherEntity)

    fun saveForecast(forecast: List<WeatherEntity>)

    fun saveLocationCurrentWeather(weatherEntity: WeatherEntity)
}
