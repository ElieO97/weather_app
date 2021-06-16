package com.elieomatuku.data.source.weather

import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.data.repository.weather.WeatherDataStore
import com.elieomatuku.data.repository.weather.WeatherRemote

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherRemoteDataStore(private val weatherRemote: WeatherRemote) : WeatherDataStore {
    override suspend fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity {
        return weatherRemote.getLocationCurrentWeather(lat, long)
    }

    override suspend fun getLocationWeatherFiveDayForecast(
        lat: Double,
        long: Double
    ): List<WeatherEntity> {
        return weatherRemote.getLocationWeatherFiveDayForecast(lat, long)
    }

    override suspend fun saveCurrentWeather(weatherEntity: WeatherEntity) {
        throw UnsupportedOperationException()
    }

    override suspend fun clearAllWeather() {
        throw UnsupportedOperationException()
    }
}
