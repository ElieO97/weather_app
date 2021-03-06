package com.elieomatuku.remote.model

import androidx.annotation.Keep
import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-12
 */

@Keep
data class RemoteLocation(
    val name: String?,
    val coord: Coordinates?,
    val country: String?,
    val id: Long? = null
) {

    companion object {
        fun toLocationEntity(remoteLocation: RemoteLocation): LocationEntity {
            return LocationEntity(
                name = remoteLocation.name ?: "",
                latitude = remoteLocation.coord?.lat ?: 0.0,
                longitude = remoteLocation.coord?.lon ?: 0.0,
                id = remoteLocation.id
            )
        }

        fun toLocationEntity(remoteLocation: GeoRemoteLocation): LocationEntity {
            return LocationEntity(
                name = remoteLocation.name ?: "",
                latitude = remoteLocation.lat ?: 0.0,
                longitude = remoteLocation.lon ?: 0.0,
                id = remoteLocation.id
            )
        }
    }
}
