package com.elieomatuku.data.source.weather

import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.data.repository.weather.WeatherCache
import com.elieomatuku.data.repository.weather.WeatherDataStore

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherCacheDataStore(private val weatherCache: WeatherCache) : WeatherDataStore {
    override suspend fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity? {
        return weatherCache.getLocationCurrentWeather(lat, long)
    }

    override suspend fun getLocationWeatherFiveDayForecast(
        lat: Double,
        long: Double
    ): List<WeatherEntity> {
        return weatherCache.getLocationWeatherFiveDayForecast(lat, long)
    }

    override suspend fun saveCurrentWeather(weatherEntity: WeatherEntity) {
        weatherCache.saveLocationCurrentWeather(weatherEntity)
    }

    override suspend fun saveLocationWeatherFiveDayForecast(weatherEntities: List<WeatherEntity>) {
        weatherCache.saveForecast(weatherEntities)
    }

    override suspend fun clearAllWeather() {
        return weatherCache.clearAllWeather()
    }
}
