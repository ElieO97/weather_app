package com.elieomatuku.data.repository.location

import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface LocationCache {

    fun clearAllLocations()

    fun isCached(): Boolean

    fun isExpired(): Boolean

    fun saveFavouriteLocation(location: LocationEntity)

    fun deleteFavouriteLocation(location: LocationEntity)

    fun getCurrentLocation(): LocationEntity

    fun getFavouriteLocations(): List<LocationEntity>
}
