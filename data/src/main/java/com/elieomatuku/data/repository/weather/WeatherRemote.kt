package com.elieomatuku.data.repository.weather

import com.elieomatuku.data.model.WeatherEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface WeatherRemote {
    fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity

    fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<WeatherEntity>
}
