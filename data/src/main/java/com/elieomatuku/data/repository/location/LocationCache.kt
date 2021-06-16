package com.elieomatuku.data.repository.location

import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface LocationCache {

    fun clearAllLocations()

    fun isCached(lat: Double, long: Double): Boolean

    fun isExpiredCurrentLocation(lat: Double, long: Double): Boolean

    fun saveFavouriteLocation(location: LocationEntity)

    fun saveCurrentLocation(location: LocationEntity)

    fun deleteFavouriteLocation(location: LocationEntity)

    fun getCurrentLocation(): LocationEntity

    fun getFavouriteLocations(): List<LocationEntity>
}
