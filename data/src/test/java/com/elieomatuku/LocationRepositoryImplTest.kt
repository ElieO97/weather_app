package com.elieomatuku

import com.elieomatuku.data.LocationRepositoryImpl
import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.repository.location.LocationCache
import com.elieomatuku.data.repository.location.LocationRemote
import com.elieomatuku.data.source.location.LocationCacheDataStore
import com.elieomatuku.data.source.location.LocationDataStoreFactory
import com.elieomatuku.data.source.location.LocationRemoteDataStore
import com.elieomatuku.domain.model.Location
import com.nhaarman.expect.expect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationRepositoryImplTest {

    lateinit var locationRepositoryImpl: LocationRepositoryImpl
    lateinit var factory: LocationDataStoreFactory

    private lateinit var locationCacheDataStore: LocationCacheDataStore
    private lateinit var locationRemoteDataStore: LocationRemoteDataStore

    lateinit var mockLocationCache: LocationCache
    lateinit var mockLocationRemote: LocationRemote

    @Before
    fun setUp() {

        mockLocationCache = mock<LocationCache>()
        mockLocationRemote = mock<LocationRemote>()

        locationCacheDataStore = LocationCacheDataStore(mockLocationCache)
        locationRemoteDataStore = LocationRemoteDataStore(mockLocationRemote)

        factory = LocationDataStoreFactory(
            mockLocationCache,
            locationCacheDataStore,
            locationRemoteDataStore
        )
        locationRepositoryImpl = LocationRepositoryImpl(factory)
    }

    @Test
    fun getCurrentLocationFromCache() {
        runBlocking {
            doReturn(
                true
            ).`when`(mockLocationCache).isCached(any(), any())

            doReturn(
                LocationEntity(
                    name = "Cape Town CBD",
                    longitude = 39.0,
                    latitude = 23.0
                )

            ).`when`(mockLocationCache).getCurrentLocation()

            doReturn(
                LocationEntity(
                    name = "Rondebosch",
                    longitude = 45.0,
                    latitude = 23.0
                )

            ).`when`(mockLocationRemote).getCurrentLocation(any(), any())

            val result = locationRepositoryImpl.getCurrentLocation(23.0, 45.0)

            expect(result).toBe(
                Location(
                    name = "Cape Town CBD",
                    longitude = 39.0,
                    latitude = 23.0

                )
            )
        }
    }

    @Test
    fun getCurrentLocationFromRemote() {
        runBlocking {
            doReturn(
                true
            ).`when`(mockLocationCache).isExpiredCurrentLocation(any(), any())

            doReturn(
                LocationEntity(
                    name = "Cape Town CBD",
                    longitude = 39.0,
                    latitude = 23.0
                )

            ).`when`(mockLocationCache).getCurrentLocation()

            doReturn(
                LocationEntity(
                    name = "Rondebosch",
                    longitude = 45.0,
                    latitude = 23.0
                )

            ).`when`(mockLocationRemote).getCurrentLocation(any(), any())

            val result = locationRepositoryImpl.getCurrentLocation(23.0, 45.0)

            expect(result).toBe(
                Location(
                    name = "Rondebosch",
                    longitude = 45.0,
                    latitude = 23.0

                )
            )
        }
    }
}
