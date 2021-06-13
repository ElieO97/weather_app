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
    val country: String?
) {

    companion object {
        fun toLocationEntity(remoteLocation: RemoteLocation): LocationEntity {
            return LocationEntity(
                name = remoteLocation.name ?: "",
                latitude = remoteLocation.coord?.lat ?: 0.0,
                longitude = remoteLocation.coord?.long ?: 0.0
            )
        }
    }
}
