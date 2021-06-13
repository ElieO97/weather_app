package com.elieomatuku.data.source.location

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.repository.location.LocationCache
import com.elieomatuku.data.repository.location.LocationDataStore

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationCacheDataStore(private val locationCache: LocationCache) : LocationDataStore {
    override fun clearAllLocations() {
        locationCache.clearAllLocations()
    }

    override fun saveFavouriteLocation(location: LocationEntity) {
        locationCache.saveFavouriteLocation(location)
    }

    override fun deleteFavouriteLocation(location: LocationEntity) {
        locationCache.deleteFavouriteLocation(location)
    }

    override fun getCurrentLocation(lat: Double, long: Double): LocationEntity {
        return locationCache.getCurrentLocation()
    }

    override fun getFavouriteLocations(): List<LocationEntity> {
        return locationCache.getFavouriteLocations()
    }
}
