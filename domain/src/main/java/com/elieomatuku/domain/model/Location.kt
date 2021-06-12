package com.elieomatuku.domain.model


/**
 * Created by elieomatuku on 2021-06-12
 */

data class Location(
        val locationId: Long,
        val name: String,
        val latitude: Double,
        val longitude: Double
)
