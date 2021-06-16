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

    @Query("SELECT * FROM ${CachedWeather.WEATHER_TABLE} where locationLatitude = :lat and locationLongitude = :long and currentWeather = null")
    fun getLocationWeatherFiveDayForecast(lat: Double, long: Double): List<CachedWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeather(weather: CachedWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeathers(weathers: List<CachedWeather>)

    @Query("DELETE FROM ${CachedWeather.WEATHER_TABLE} WHERE locationLatitude = :lat And locationLongitude = :long")
    fun deleteWeatherForLocation(lat: Double, long: Double)

    @Query("DELETE FROM ${CachedWeather.WEATHER_TABLE} WHERE locationLatitude = :lat And locationLongitude = :long and currentWeather = 1")
    fun deleteCurrentWeatherForLocation(lat: Double, long: Double)

    @Query("DELETE FROM ${CachedWeather.WEATHER_TABLE}")
    fun deleteAll()
}
