package com.elieomatuku.data.model

import com.elieomatuku.domain.model.Location

/**
 * Created by elieomatuku on 2021-06-12
 */

data class LocationEntity(
    val name: String,
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        fun toLocation(locationEntity: LocationEntity): Location {
            return Location(
                name = locationEntity.name,
                latitude = locationEntity.latitude,
                longitude = locationEntity.longitude
            )
        }
    }
}
