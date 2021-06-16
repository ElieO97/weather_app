package com.elieomatuku.data

import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.data.source.weather.WeatherDataStoreFactory
import com.elieomatuku.data.source.weather.WeatherRemoteDataStore
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.repository.WeatherRepository

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherRepositoryImpl(private val factory: WeatherDataStoreFactory) : WeatherRepository {
    override suspend fun getLocationCurrentWeather(lat: Double, long: Double): Weather {
        try {
            val dataStore = factory.retrieveDataStore(lat, long)
            var weatherEntity = dataStore.getLocationCurrentWeather(lat, long)
            val location = weatherEntity?.location?.copy(latitude = lat, longitude = long)
            weatherEntity = weatherEntity?.copy(location = location!!)

            if (dataStore is WeatherRemoteDataStore) {
                factory.retrieveCacheDataStore().saveCurrentWeather(weatherEntity!!)
            }

            return weatherEntity!!.let(WeatherEntity::toWeather)
        } catch (e: Exception) {
            if (factory.isCached(lat, long)) {
                val weatherEntity =
                    factory.retrieveCacheDataStore().getLocationCurrentWeather(lat, long)!!
                return weatherEntity.let(WeatherEntity::toWeather)
            } else {
                throw e
            }
        }
    }

    override suspend fun getLocationWeatherFiveDayForecast(
        lat: Double,
        long: Double
    ): List<Weather> {

        try {
            val dataStore = factory.retrieveDataStore(lat, long)
            val weatherEntities = dataStore.getLocationWeatherFiveDayForecast(lat, long).map {
                val location = it.location.copy(latitude = lat, longitude = long)
                it.copy(location = location)
            }.sortedBy { it.date }

            if (dataStore is WeatherRemoteDataStore) {
                factory.retrieveCacheDataStore().saveLocationWeatherFiveDayForecast(weatherEntities)
            }

            return weatherEntities
                .map {
                    val weather: Weather = WeatherEntity.toWeather(it)
                    weather
                }
        } catch (e: Exception) {
            if (factory.isCached(lat, long)) {
                val weatherEntities =
                    factory.retrieveCacheDataStore().getLocationWeatherFiveDayForecast(lat, long)

                return weatherEntities
                    .map {
                        val weather: Weather = WeatherEntity.toWeather(it)
                        weather
                    }.sortedBy { it.date }
            } else {
                throw e
            }
        }
    }
}
