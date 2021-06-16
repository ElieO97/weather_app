package com.elieomatuku.cache.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-12
 */

@Entity(tableName = CachedLocation.LOCATION_TABLE)
data class CachedLocation(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val currentLocation: Boolean? = null,
    val favouriteLocation: Boolean? = null
) {
    companion object {
        const val LOCATION_TABLE = "location_table"

        fun toLocationEntity(cachedLocation: CachedLocation): LocationEntity {
            return LocationEntity(
                name = cachedLocation.name,
                latitude = cachedLocation.latitude,
                longitude = cachedLocation.longitude
            )
        }

        fun toCacheLocation(
            location: LocationEntity,
            currentLocation: Boolean? = null,
            favouriteLocation: Boolean? = null
        ): CachedLocation {
            return CachedLocation(
                name = location.name,
                latitude = location.latitude,
                longitude = location.longitude,
                currentLocation = currentLocation,
                favouriteLocation = favouriteLocation
            )
        }
    }
}
