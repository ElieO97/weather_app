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
        const val STALE_MS = 300000 // Data is stale after a 5min
    }

    override fun clearAllWeather() {
        weatherDao.deleteAll()
    }

    override fun clearWeatherForLocation(locationId: Long) {
        weatherDao.deleteWeatherForLocation(locationId)
    }

    override fun isCached(lat: Double, long: Double): Boolean {
        val currentWeather: WeatherEntity? = getLocationCurrentWeather(lat, long)
        return currentWeather != null
    }

    override fun isExpired(lat: Double, long: Double): Boolean {
        val currentWeather: WeatherEntity? = getLocationCurrentWeather(lat, long)

        return currentWeather?.let {
            System.currentTimeMillis() - it.lastUpdatedInMilliseconds > STALE_MS
        } ?: true
    }

    override fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity? {
        val cacheWeather = weatherDao.getLocationCurrentWeather(lat, long)
        return cacheWeather?.let(CachedWeather::toWeatherEntity)
    }

    override fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<WeatherEntity> {
        val cachedForecast = weatherDao.getLocationWeatherFiveDayForecast(lat, long)

        return cachedForecast.map {
            CachedWeather.toWeatherEntity(it)
        }
    }

    override fun saveWeather(weatherEntity: WeatherEntity) {
        val cachedWeather = CachedWeather.fromWeatherEntity(weatherEntity)
        weatherDao.saveWeather(cachedWeather)
    }

    override fun saveForecast(forecast: List<WeatherEntity>) {
        weatherDao.deleteLocationWeatherFiveDayForecast(forecast.first().location.id)

        val cacheForecast = forecast.map {
            CachedWeather.fromWeatherEntity(it, currentWeather = false)
        }

        weatherDao.savForecast(cacheForecast)
    }

    override fun saveLocationCurrentWeather(weatherEntity: WeatherEntity) {
        weatherDao.deleteCurrentWeatherForLocation(
            weatherEntity.location.id
        )
        val cachedWeather = CachedWeather.fromWeatherEntity(weatherEntity, currentWeather = true)
        weatherDao.saveWeather(cachedWeather)
    }
}
