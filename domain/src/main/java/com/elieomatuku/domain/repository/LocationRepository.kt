package com.elieomatuku.domain.repository

import com.elieomatuku.domain.model.Location


/**
 * Created by elieomatuku on 2021-06-12
 */

interface LocationRepository {
    suspend fun getCurrentLocation(): Location
    suspend fun getFavouritesLocations(): List<Location>
    suspend fun getLocationDetails(name: String): Location
    suspend fun getLocationDetails(lat: Double, long: Double): Location
    suspend fun saveFavouriteLocation(location: Location)
}