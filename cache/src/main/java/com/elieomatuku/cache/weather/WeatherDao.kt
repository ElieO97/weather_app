package com.elieomatuku.cache.weather

import androidx.room.Dao

/**
 * Created by elieomatuku on 2021-06-12
 */

@Dao
interface WeatherDao {

    fun getLocationCurrentWeather(lat: Double, long: Double)
}
