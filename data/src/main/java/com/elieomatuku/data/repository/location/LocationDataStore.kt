package com.elieomatuku.data.repository.location

import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface LocationDataStore {

    suspend fun clearAllLocations()

    suspend fun saveCurrentLocation(location: LocationEntity)

    suspend fun saveFavouriteLocation(location: LocationEntity)

    suspend fun deleteFavouriteLocation(location: LocationEntity)

    suspend fun getCurrentLocation(lat: Double, long: Double): LocationEntity

    suspend fun getFavouriteLocations(): List<LocationEntity>

    suspend fun searchLocation(name: String): List<LocationEntity>

    suspend fun getAllLocations(): List<LocationEntity>
}
