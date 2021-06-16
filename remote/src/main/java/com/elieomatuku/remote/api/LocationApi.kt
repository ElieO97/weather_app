package com.elieomatuku.remote.api

import com.elieomatuku.remote.model.RemoteLocation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by elieomatuku on 2021-06-13
 */

interface LocationApi {

    @GET("geo/1.0/reverse")
    suspend fun getLocation(
        @Query("lat") lat: Double,
        @Query("lon") long: Double
    ): Response<List<RemoteLocation>>

    @GET("geo/1.0/reverse")
    suspend fun getLocation(@Query("q") q: String): Response<List<RemoteLocation>>
}
