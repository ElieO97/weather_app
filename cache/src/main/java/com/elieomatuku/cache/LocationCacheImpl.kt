package com.elieomatuku.cache

import com.elieomatuku.cache.location.LocationDao
import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.repository.location.LocationCache

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationCacheImpl(locationDao: LocationDao) : LocationCache {
    override fun clearAllLocations() {
        TODO("Not yet implemented")
    }

    override fun isCached(): Boolean {
        return false
    }

    override fun isExpired(): Boolean {
        return true
    }

    override fun saveFavouriteLocation(location: LocationEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteFavouriteLocation(location: LocationEntity) {
        TODO("Not yet implemented")
    }

    override fun getCurrentLocation(): LocationEntity {
        TODO("Not yet implemented")
    }

    override fun getFavouriteLocations(): List<LocationEntity> {
        TODO("Not yet implemented")
    }
}
