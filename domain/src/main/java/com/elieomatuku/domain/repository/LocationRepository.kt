package com.elieomatuku.domain.repository

import com.elieomatuku.domain.model.Location

/**
 * Created by elieomatuku on 2021-06-12
 */

interface LocationRepository {
    suspend fun getCurrentLocation(lat: Double, long: Double): Location
    suspend fun getFavouritesLocations(): List<Location>
    suspend fun getLocationDetails(lat: Double, long: Double): Location
    suspend fun saveFavouriteLocation(location: Location)
    suspend fun saveCurrentLocation(location: Location)
    suspend fun deleteFavouriteLocation(location: Location)
    suspend fun searchLocation(name: String): List<Location>
}
