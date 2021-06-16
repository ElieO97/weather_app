package com.elieomatuku.remote

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.repository.location.LocationRemote
import com.elieomatuku.remote.api.LocationApi
import com.elieomatuku.remote.api.RemoteException
import com.elieomatuku.remote.model.RemoteLocation

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationRemoteImpl(private val locationApi: LocationApi) : LocationRemote {
    override suspend fun getCurrentLocation(lat: Double, long: Double): LocationEntity {
        val response = locationApi.getLocation(lat, long)
        if (response.isSuccessful) {
            val locationResponse = response.body()
            return locationResponse?.first()?.let(RemoteLocation::toLocationEntity)!!
        } else {
            throw RemoteException(
                response.code(),
                response.errorBody()?.toString(),
                response.message()
            )
        }
    }

    override suspend fun searchLocation(name: String): List<LocationEntity> {
        val response = locationApi.getLocation(name)
        if (response.isSuccessful) {
            val locationResponse = response.body()
            return locationResponse?.map {
                RemoteLocation.toLocationEntity(it)
            }!!
        } else {
            throw RemoteException(
                response.code(),
                response.errorBody()?.toString(),
                response.message()
            )
        }
    }
}
