package com.elieomatuku.data.model

import com.elieomatuku.domain.model.Location

/**
 * Created by elieomatuku on 2021-06-12
 */

data class LocationEntity(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val id: Long? = null,
    val isCurrentLocation: Boolean = false
) {
    companion object {
        fun toLocation(locationEntity: LocationEntity): Location {
            return Location(
                name = locationEntity.name,
                latitude = locationEntity.latitude,
                longitude = locationEntity.longitude,
                id = locationEntity.id,
                isCurrentLocation = locationEntity.isCurrentLocation
            )
        }

        fun fromLocation(location: Location): LocationEntity {
            return LocationEntity(
                name = location.name,
                latitude = location.latitude,
                longitude = location.longitude,
                id = location.id,
                isCurrentLocation = location.isCurrentLocation
            )
        }
    }
}
