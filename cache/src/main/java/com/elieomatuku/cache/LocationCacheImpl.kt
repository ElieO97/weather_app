package com.elieomatuku.cache

import com.elieomatuku.cache.location.CachedLocation
import com.elieomatuku.cache.location.LocationDao
import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.repository.location.LocationCache

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationCacheImpl(private val locationDao: LocationDao) : LocationCache {
    override fun clearAllLocations() {
        locationDao.deleteAll()
    }

    override fun isCached(lat: Double, long: Double): Boolean {
        val cachedLocation: CachedLocation? = locationDao.getLocation(lat, long)
        return cachedLocation != null
    }

    override fun saveFavouriteLocation(location: LocationEntity) {
        val cachedLocation = CachedLocation.toCacheLocation(location, favouriteLocation = true)
        if (locationDao.getLocation(cachedLocation.latitude, cachedLocation.longitude) != null) {
            locationDao.deleteFavouriteLocation(cachedLocation.latitude, cachedLocation.longitude)
        }

        locationDao.saveLocation(cachedLocation)
    }

    override fun saveCurrentLocation(location: LocationEntity) {
        val cachedLocation = CachedLocation.toCacheLocation(location, currentLocation = true)
        if (locationDao.getCurrentLocation() != null) {
            locationDao.deleteCurrentLocation()
        }
        locationDao.saveLocation(cachedLocation)
    }

    override fun deleteFavouriteLocation(location: LocationEntity) {
        locationDao.deleteFavouriteLocation(location.latitude, location.longitude)
    }

    override fun getCurrentLocation(): LocationEntity {
        val currentLocation = locationDao.getCurrentLocation()!!
        return currentLocation.let(CachedLocation::toLocationEntity)
    }

    override fun getFavouriteLocations(): List<LocationEntity> {
        val currentLocation = locationDao.getFavouritesLocations()
        return currentLocation.map {
            CachedLocation.toLocationEntity(it)
        }
    }

    override fun getAllLocations(): List<LocationEntity> {
        return locationDao.getAllLocations().map {
            CachedLocation.toLocationEntity(it)
        }
    }

    override fun isExpiredCurrentLocation(lat: Double, long: Double): Boolean {
        val currentLocation = getCurrentLocation()
        return ((lat != currentLocation.latitude) && (long != currentLocation.longitude))
    }
}
