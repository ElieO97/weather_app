package com.elieomatuku.domain.interactor.weather

import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.model.WeatherCondition
import com.elieomatuku.domain.repository.RepositoryException
import com.elieomatuku.domain.repository.WeatherRepository
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

class GetFavouriteLocationCurrentWeatherTest {

    private lateinit var getLocationCurrentWeather: GetFavouriteLocationCurrentWeather

    private lateinit var mockWeatherRepository: WeatherRepository

    @Before
    fun setUp() {
        mockWeatherRepository = mock<WeatherRepository> {
            onBlocking { getLocationCurrentWeather(any(), any()) } doReturn Weather(
                temperature = 21.0,
                minimumTemperature = 16.0,
                maximumTemperature = 25.0,
                weatherCondition = WeatherCondition.Cloudy,
                location = Location(
                    "Cape Town",
                    75.0,
                    89.0
                ),
                date = 1245955959,
                lastUpdate = 3388339304494
            )
        }

        getLocationCurrentWeather = GetFavouriteLocationCurrentWeather(mockWeatherRepository)
    }

    @Test
    fun success() {
        runBlocking {
            val result =
                runUseCase(getLocationCurrentWeather, GetFavouriteLocationCurrentWeather.Input(75.0, 89.0))
            expect(result).toBe(
                Success(
                    Weather(
                        temperature = 21.0,
                        minimumTemperature = 16.0,
                        maximumTemperature = 25.0,
                        weatherCondition = WeatherCondition.Cloudy,
                        location = Location(
                            "Cape Town",
                            75.0,
                            89.0
                        ),
                        date = 1245955959,
                        lastUpdate = 3388339304494
                    )
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
            ).`when`(mockWeatherRepository).getLocationCurrentWeather(any(), any())

            val result =
                runUseCase(getLocationCurrentWeather, GetFavouriteLocationCurrentWeather.Input(75.0, 89.0))
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
