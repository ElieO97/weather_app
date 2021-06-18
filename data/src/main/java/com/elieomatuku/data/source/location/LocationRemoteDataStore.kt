package com.elieomatuku.data.source.location

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.repository.location.LocationDataStore
import com.elieomatuku.data.repository.location.LocationRemote

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationRemoteDataStore(private val locationRemote: LocationRemote) : LocationDataStore {
    override suspend fun clearAllLocations() {
        throw UnsupportedOperationException()
    }

    override suspend fun saveCurrentLocation(location: LocationEntity) {
        throw UnsupportedOperationException()
    }

    override suspend fun saveFavouriteLocation(location: LocationEntity) {
        throw UnsupportedOperationException()
    }

    override suspend fun deleteFavouriteLocation(location: LocationEntity) {
        throw UnsupportedOperationException()
    }

    override suspend fun getCurrentLocation(lat: Double, long: Double): LocationEntity {
        return locationRemote.getCurrentLocation(lat, long)
    }

    override suspend fun getFavouriteLocations(): List<LocationEntity> {
        throw UnsupportedOperationException()
    }

    override suspend fun searchLocation(name: String): List<LocationEntity> {
        return locationRemote.searchLocation(name)
    }
}
