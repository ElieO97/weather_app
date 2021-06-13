package com.elieomatuku.data.source.location

import com.elieomatuku.data.repository.location.LocationCache
import com.elieomatuku.data.repository.location.LocationDataStore

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationDataStoreFactory(
    private val locationCache: LocationCache,
    private val locationCacheDataStore: LocationCacheDataStore,
    private val locationRemoteDataStore: LocationRemoteDataStore
) {

    fun retrieveDataStore(): LocationDataStore {
        return if (locationCache.isCached() && !locationCache.isExpired()) {
            retrieveCacheDataStore()
        } else {
            retrieveRemoteDataStore()
        }
    }

    fun retrieveCacheDataStore(): LocationDataStore {
        return locationCacheDataStore
    }

    fun retrieveRemoteDataStore(): LocationDataStore {
        return locationRemoteDataStore
    }
}
