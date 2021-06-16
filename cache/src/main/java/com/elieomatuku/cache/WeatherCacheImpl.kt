package com.elieomatuku.cache

import com.elieomatuku.cache.weather.CachedWeather
import com.elieomatuku.cache.weather.WeatherDao
import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.data.repository.weather.WeatherCache

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherCacheImpl(private val weatherDao: WeatherDao) : WeatherCache {
    companion object {
        const val STALE_MS = 360 // Data is stale after a 1min
    }

    override fun clearAllWeather() {
        weatherDao.deleteAll()
    }

    override fun clearWeatherForLocation(lat: Double, long: Double) {
        weatherDao.deleteWeatherForLocation(lat, long)
    }

    override fun isCached(lat: Double, long: Double): Boolean {
        val currentWeather: WeatherEntity? = getLocationCurrentWeather(lat, long)
        val forecast = getLocationWeatherFiveDayForecast(lat, long)
        return currentWeather != null && forecast.isNullOrEmpty()
    }

    override fun isExpired(lat: Double, long: Double): Boolean {
        val currentWeather: WeatherEntity? = getLocationCurrentWeather(lat, long)

        return currentWeather?.let {
            System.currentTimeMillis() - it.lastUpdatedInMilliseconds < STALE_MS
        } ?: true
    }

    override fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity? {
        val cacheWeather = weatherDao.getLocationCurrentWeather(lat, long)
        return cacheWeather.let(CachedWeather::toWeatherEntity)
    }

    override fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<WeatherEntity> {
        val cachedWeathers = weatherDao.getLocationWeatherFiveDayForecast(lat, long)

        return cachedWeathers.map {
            CachedWeather.toWeatherEntity(it)
        }
    }

    override fun saveWeather(weatherEntity: WeatherEntity, currentWeather: Boolean?) {
        val cachedWeather = CachedWeather.fromWeatherEntity(weatherEntity, currentWeather)
        weatherDao.saveWeather(cachedWeather)
    }
}
