package com.elieomatuku.cache.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by elieomatuku on 2021-06-12
 */

@Dao
interface LocationDao {

    @Query("SELECT * FROM ${CachedLocation.LOCATION_TABLE} where currentLocation = 1 LIMIT 1")
    fun getCurrentLocation(): CachedLocation

    @Query("SELECT * FROM ${CachedLocation.LOCATION_TABLE} where favouriteLocation = 1")
    fun getFavouritesLocations(): List<CachedLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteLocation(cachedLocation: CachedLocation)

    @Query("DELETE FROM ${CachedLocation.LOCATION_TABLE} WHERE latitude = :lat And longitude = :long")
    fun deleteFavouriteLocation(lat: Double, long: Double)
}
