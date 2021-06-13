package com.elieomatuku.remote.api

import com.elieomatuku.remote.model.RemoteForecast
import com.elieomatuku.remote.model.RemoteWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by elieomatuku on 2021-06-13
 */

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getLocationCurrentWeather(
        @Path("lat") lat: Double,
        @Path("long") long: Double
    ): Response<RemoteWeather>

    @GET("data/2.5/forecast")
    suspend fun getLocationWeatherFiveDayForecast(
        @Path("lat") lat: Double,
        @Path("long") long: Double
    ): Response<RemoteForecast>
}
