package com.elieomatuku.data.repository.location

import com.elieomatuku.data.model.LocationEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

interface LocationRemote {

    fun getCurrentLocation(lat: Double, long: Double): LocationEntity

    fun searchLocation(name: String): List<LocationEntity>
}
