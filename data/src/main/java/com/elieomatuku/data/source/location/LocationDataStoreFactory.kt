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

    fun retrieveDataStore(lat: Double, long: Double): LocationDataStore {
        return if (locationCache.isCached(lat, long)) {
            retrieveCacheDataStore()
        } else {
            retrieveRemoteDataStore()
        }
    }

    fun retrieveCurrentLocationDataStore(lat: Double, long: Double): LocationDataStore {
        return if (locationCache.isExpiredCurrentLocation(lat, long)) {
            retrieveDataStore(lat, long)
        } else {
            retrieveCacheDataStore()
        }
    }

    fun retrieveCacheDataStore(): LocationDataStore {
        return locationCacheDataStore
    }

    fun retrieveRemoteDataStore(): LocationDataStore {
        return locationRemoteDataStore
    }
}
