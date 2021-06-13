package com.elieomatuku.data.repository.location

import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface LocationDataStore {

    fun clearAllLocations()

    fun saveFavouriteLocation(location: LocationEntity)

    fun deleteFavouriteLocation(location: LocationEntity)

    fun getCurrentLocation(lat: Double, long: Double): LocationEntity

    fun getFavouriteLocations(): List<LocationEntity>

}
