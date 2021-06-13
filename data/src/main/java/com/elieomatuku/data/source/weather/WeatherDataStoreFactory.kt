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

    fun retrieveDataStore(): WeatherDataStore {
        return if (weatherCache.isCached() && !weatherCache.isExpired()) {
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
}
