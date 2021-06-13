package com.elieomatuku.data.repository.location

import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface LocationDataStore {

    suspend fun clearAllLocations()

    suspend fun saveFavouriteLocation(location: LocationEntity)

    suspend fun deleteFavouriteLocation(location: LocationEntity)

    suspend fun getCurrentLocation(lat: Double, long: Double): LocationEntity

    suspend fun getFavouriteLocations(): List<LocationEntity>
}
