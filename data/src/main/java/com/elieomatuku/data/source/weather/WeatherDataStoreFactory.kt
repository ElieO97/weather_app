package com.elieomatuku.data.source.weather

import com.elieomatuku.data.repository.weather.WeatherCache
import com.elieomatuku.data.repository.weather.WeatherDataStore

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherDataStoreFactory(
    private val weatherCache: WeatherCache,
    private val weatherCacheDataStore: WeatherCacheDataStore,
    private val weatherRemoteDataStore: WeatherRemoteDataStore
) {

    fun retrieveDataStore(lat: Double, long: Double): WeatherDataStore {
        return if (weatherCache.isCached(lat, long) && !weatherCache.isExpired(lat, long)) {
            retrieveCacheDataStore()
        } else {
            retrieveRemoteDataStore()
        }
    }

    fun retrieveCacheDataStore(): WeatherDataStore {
        return weatherCacheDataStore
    }

    fun retrieveRemoteDataStore(): WeatherDataStore {
        return weatherRemoteDataStore
    }

    fun isCached(lat: Double, long: Double): Boolean {
        return weatherCache.isCached(lat, long)
    }
}
