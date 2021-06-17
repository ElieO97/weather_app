package com.elieomatuku.cache.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by elieomatuku on 2021-06-12
 */

@Dao
interface WeatherDao {

    @Query("SELECT * FROM ${CachedWeather.WEATHER_TABLE} where locationLatitude = :lat and locationLongitude = :long and currentWeather = 1  LIMIT 1")
    fun getLocationCurrentWeather(lat: Double, long: Double): CachedWeather?

    @Query("SELECT * FROM ${CachedWeather.WEATHER_TABLE} where locationLatitude = :lat and locationLongitude = :long and currentWeather = 0")
    fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<CachedWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeather(weather: CachedWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savForecast(forecast: List<CachedWeather>)

    @Query("DELETE FROM ${CachedWeather.WEATHER_TABLE} WHERE locationId = :locationId")
    fun deleteWeatherForLocation(locationId: Long?)

    @Query("DELETE FROM ${CachedWeather.WEATHER_TABLE} WHERE locationId = :locationId and currentWeather = 1")
    fun deleteCurrentWeatherForLocation(locationId: Long?)

    @Query("DELETE FROM ${CachedWeather.WEATHER_TABLE} where locationId = :locationId and currentWeather = 0")
    fun deleteLocationWeatherFiveDayForecast(locationId: Long?)

    @Query("DELETE FROM ${CachedWeather.WEATHER_TABLE}")
    fun deleteAll()
}
