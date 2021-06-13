package com.elieomatuku.cache

import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.data.repository.weather.WeatherCache

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherCacheImpl : WeatherCache {
    override fun clearAllWeather() {
        TODO("Not yet implemented")
    }

    override fun isCached(): Boolean {
        return false
    }

    override fun isExpired(): Boolean {
        return true
    }

    override fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity {
        TODO("Not yet implemented")
    }

    override fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<WeatherEntity> {
        TODO("Not yet implemented")
    }
}
