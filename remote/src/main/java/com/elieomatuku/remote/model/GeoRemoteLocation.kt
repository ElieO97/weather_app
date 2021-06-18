package com.elieomatuku.remote.model

import androidx.annotation.Keep

/**
 * Created by elieomatuku on 2021-06-18
 */

@Keep
data class GeoRemoteLocation(
    val name: String?,
    val lat: Double?,
    val lon: Double?,
    val country: String?,
    val id: Long? = null
)
