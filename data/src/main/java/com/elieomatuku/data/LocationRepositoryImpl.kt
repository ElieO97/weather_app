package com.elieomatuku.data

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.source.location.LocationDataStoreFactory
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationRepositoryImpl(private val factory: LocationDataStoreFactory) : LocationRepository {
    override suspend fun getCurrentLocation(lat: Double, long: Double): Location {
        return factory.retrieveDataStore().getCurrentLocation(lat, long).let(LocationEntity::toLocation)
    }

    override suspend fun getFavouritesLocations(): List<Location> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationDetails(lat: Double, long: Double): Location {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavouriteLocation(location: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavouriteLocation(location: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun searchLocation(name: String): List<Location> {
        TODO("Not yet implemented")
    }
}
