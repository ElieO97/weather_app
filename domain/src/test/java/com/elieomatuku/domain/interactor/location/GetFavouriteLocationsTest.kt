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
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock

/**
 * Created by elieomatuku on 2021-06-12
 */

class GetFavouriteLocationsTest {

    private lateinit var getFavouriteLocations: GetFavouriteLocations
    private lateinit var mockLocationRepository: LocationRepository

    @Before
    fun setUp() {
        mockLocationRepository = mock<LocationRepository> {
            onBlocking { getFavouritesLocations() } doReturn listOf(
                Location(
                    "Cape Town",
                    75.0,
                    89.0
                )
            )
        }

        getFavouriteLocations = GetFavouriteLocations(mockLocationRepository)
    }

    @Test
    fun success() {
        runBlocking {

            val result = runUseCase(getFavouriteLocations, Unit)
            expect(result).toBe(
                Success(
                    listOf(Location("Cape Town", 75.0, 89.0))
                )
            )
        }
    }

    @Test
    fun emptyResult() {
        runBlocking {
            doReturn(
                emptyList<Location>()
            ).`when`(mockLocationRepository).getFavouritesLocations()

            val result = runUseCase(getFavouriteLocations, Unit)
            expect(result).toBe(
                Success(
                    emptyList()
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
            ).`when`(mockLocationRepository).getFavouritesLocations()

            val result = runUseCase(getFavouriteLocations, Unit)
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
