package com.elieomatuku.data.repository.weather

import com.elieomatuku.data.model.WeatherEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface WeatherDataStore {
    suspend fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity

    suspend fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<WeatherEntity>

    suspend fun clearAllWeather()
}
