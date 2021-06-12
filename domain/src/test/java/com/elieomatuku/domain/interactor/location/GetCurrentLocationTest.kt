package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository
import com.elieomatuku.domain.repository.RepositoryException
import com.nhaarman.expect.expect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock


/**
 * Created by elieomatuku on 2021-06-12
 */

class GetCurrentLocationTest {

    private lateinit var getCurrentLocation: GetCurrentLocation

    private lateinit var mockLocationRepository: LocationRepository

    @Before
    fun setUp() {
        mockLocationRepository = mock<LocationRepository> {
            onBlocking { getCurrentLocation(any(), any()) } doReturn Location(
                "Cape Town",
                75.0,
                89.0
            )
        }

        getCurrentLocation = GetCurrentLocation(mockLocationRepository)
    }

    @Test
    fun success() {
        runBlocking {
            val result = runUseCase(getCurrentLocation, GetCurrentLocation.Input(75.0, 89.0))
            expect(result).toBe(
                Success(
                    Location("Cape Town", 75.0, 89.0)
                )
            )
        }
    }

    @Test
    fun failure() {
        runBlocking {
            doThrow(
                RepositoryException(
                    500,
                    "error",
                    "something went wrong"
                )
            ).`when`(mockLocationRepository).getCurrentLocation(any(), any())

            val result = runUseCase(getCurrentLocation, GetCurrentLocation.Input(75.0, 89.0))
            expect(result).toBe(
                Fail(
                    RepositoryException(
                        500,
                        "error",
                        "something went wrong"
                    )
                )
            )

        }
    }
}