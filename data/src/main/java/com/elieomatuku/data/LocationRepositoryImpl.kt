package com.elieomatuku.data

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.source.location.LocationDataStoreFactory
import com.elieomatuku.data.source.location.LocationRemoteDataStore
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationRepositoryImpl(private val factory: LocationDataStoreFactory) : LocationRepository {
    override suspend fun getCurrentLocation(lat: Double, long: Double): Location {
        try {
            val dataStore = factory.retrieveCurrentLocationDataStore(lat, long)
            val locationEntity = dataStore.getCurrentLocation(lat, long)

            if (dataStore is LocationRemoteDataStore) {
                factory.retrieveCacheDataStore().saveCurrentLocation(locationEntity)
            }

            return locationEntity.let(LocationEntity::toLocation)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getFavouritesLocations(): List<Location> {
        val locations = factory.retrieveCacheDataStore().getFavouriteLocations()
        return locations.map {
            LocationEntity.toLocation(it)
        }
    }

    override suspend fun getLocationDetails(lat: Double, long: Double): Location {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavouriteLocation(location: Location) {
        factory.retrieveCacheDataStore().saveFavouriteLocation(location.let(LocationEntity::fromLocation))
    }

    override suspend fun deleteFavouriteLocation(location: Location) {
        factory.retrieveCacheDataStore().deleteFavouriteLocation(location.let(LocationEntity::fromLocation))
    }

    override suspend fun searchLocation(name: String): List<Location> {
        TODO("Not yet implemented")
    }
}
