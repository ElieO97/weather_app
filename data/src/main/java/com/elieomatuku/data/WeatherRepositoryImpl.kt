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
        val dataStore = factory.retrieveDataStore(lat, long)
        val weatherEntity = dataStore.getLocationCurrentWeather(lat, long)

        if (dataStore is WeatherRemoteDataStore) {
            factory.retrieveCacheDataStore().saveCurrentWeather(weatherEntity)
        }

        return weatherEntity.let(WeatherEntity::toWeather)
    }

    override suspend fun getLocationWeatherFiveDayForecast(
        lat: Double,
        long: Double
    ): List<Weather> {

        return factory.retrieveDataStore(lat, long).getLocationWeatherFiveDayForecast(lat, long)
            .map {
                val weather: Weather = WeatherEntity.toWeather(it)
                weather
            }
    }
}
