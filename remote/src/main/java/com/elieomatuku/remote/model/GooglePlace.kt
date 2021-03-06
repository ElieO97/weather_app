package com.elieomatuku.remote.model

import androidx.annotation.Keep

/**
 * Created by elieomatuku on 2021-06-13
 */

@Keep
data class GooglePlace(
    val name: String?,
    val geometry: Geometry?,
)

@Keep
data class Geometry(
    val location: GeometryCoordinates,
    val name: String?
)

@Keep
data class GeometryCoordinates(
    val lat: Double?,
    val lng: Double?
)
