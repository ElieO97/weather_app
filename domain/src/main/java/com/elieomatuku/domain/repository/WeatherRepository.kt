package com.elieomatuku.domain.repository

import com.elieomatuku.domain.model.Weather

/**
 * Created by elieomatuku on 2021-06-12
 */

interface WeatherRepository {
    suspend fun getLocationCurrentWeather(lat: Double, long: Double): Weather
    suspend fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<Weather>
}
