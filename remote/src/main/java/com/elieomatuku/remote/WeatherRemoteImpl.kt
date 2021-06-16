package com.elieomatuku.remote

import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.data.repository.weather.WeatherRemote
import com.elieomatuku.remote.api.RemoteException
import com.elieomatuku.remote.api.WeatherApi
import com.elieomatuku.remote.model.RemoteForecast
import com.elieomatuku.remote.model.RemoteWeather

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherRemoteImpl(private val weatherApi: WeatherApi) : WeatherRemote {
    override suspend fun getLocationCurrentWeather(lat: Double, long: Double): WeatherEntity {
        val response = weatherApi.getLocationCurrentWeather(lat, long)
        if (response.isSuccessful) {
            val weatherResponse = response.body()
            return weatherResponse?.let(
                RemoteWeather::toWeatherEntity
            )!!
        } else {
            throw RemoteException(
                response.code(),
                response.errorBody()?.toString(),
                response.message()
            )
        }
    }

    override suspend fun getLocationWeatherFiveDayForecast(
        lat: Double,
        long: Double
    ): List<WeatherEntity> {
        val response = weatherApi.getLocationWeatherFiveDayForecast(lat, long)
        if (response.isSuccessful) {
            var forecastResponse = response.body()
            forecastResponse = forecastResponse?.let(RemoteForecast::filterMaxTempForecasts)

            return forecastResponse?.let {
                RemoteForecast.toWeatherEntities(it)
            } ?: emptyList()
        } else {
            throw RemoteException(
                response.code(),
                response.errorBody()?.toString(),
                response.message()
            )
        }
    }
}
