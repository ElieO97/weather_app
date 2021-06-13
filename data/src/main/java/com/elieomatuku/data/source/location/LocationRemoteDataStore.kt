package com.elieomatuku.data.source.location

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.repository.location.LocationDataStore
import com.elieomatuku.data.repository.location.LocationRemote

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationRemoteDataStore(private val locationRemote: LocationRemote) : LocationDataStore {
    override fun clearAllLocations() {
        throw UnsupportedOperationException()
    }

    override fun saveFavouriteLocation(location: LocationEntity) {
        throw UnsupportedOperationException()
    }

    override fun deleteFavouriteLocation(location: LocationEntity) {
        throw UnsupportedOperationException()
    }

    override fun getCurrentLocation(lat: Double, long: Double): LocationEntity {
        return locationRemote.getCurrentLocation(lat, long)
    }

    override fun getFavouriteLocations(): List<LocationEntity> {
        throw UnsupportedOperationException()
    }
}
