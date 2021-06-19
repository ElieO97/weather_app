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

class GetLocationFiveDayForecastTest {
    private lateinit var getLocationFiveDayForecast: GetLocationFiveDayForecast

    private lateinit var mockWeatherRepository: WeatherRepository

    @Before
    fun setUp() {
        mockWeatherRepository = mock<WeatherRepository> {
            onBlocking { getLocationWeatherFiveDayForecast(any(), any()) } doReturn listOf(
                Weather(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherCondition = WeatherCondition.Cloudy,
                    weatherConditionMain = "cloudy",
                    weatherConditionId = 801,
                    weatherConditionDescription = "cloudy",
                    location = Location(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdate = 3388339304494
                ),
                Weather(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherCondition = WeatherCondition.Cloudy,
                    weatherConditionMain = "cloudy",
                    weatherConditionId = 801,
                    weatherConditionDescription = "cloudy",
                    location = Location(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdate = 3388339304494
                ),
                Weather(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherCondition = WeatherCondition.Cloudy,
                    weatherConditionMain = "cloudy",
                    weatherConditionId = 801,
                    weatherConditionDescription = "cloudy",
                    location = Location(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdate = 3388339304494
                ),
                Weather(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherCondition = WeatherCondition.Cloudy,
                    weatherConditionMain = "cloudy",
                    weatherConditionId = 801,
                    weatherConditionDescription = "cloudy",
                    location = Location(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdate = 3388339304494
                ),
                Weather(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherCondition = WeatherCondition.Cloudy,
                    weatherConditionMain = "cloudy",
                    weatherConditionId = 801,
                    weatherConditionDescription = "cloudy",
                    location = Location(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdate = 3388339304494
                )
            )
        }

        getLocationFiveDayForecast = GetLocationFiveDayForecast(mockWeatherRepository)
    }

    @Test
    fun success() {
        runBlocking {
            val result =
                runUseCase(getLocationFiveDayForecast, GetLocationFiveDayForecast.Input(75.0, 89.0))
            expect(result).toBe(
                Success(
                    listOf(
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
                            lastUpdate = 3388339304494,
                            weatherConditionMain = "cloudy",
                            weatherConditionId = 801,
                            weatherConditionDescription = "cloudy",
                        ),
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
                            lastUpdate = 3388339304494,
                            weatherConditionMain = "cloudy",
                            weatherConditionId = 801,
                            weatherConditionDescription = "cloudy",
                        ),
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
                            lastUpdate = 3388339304494,
                            weatherConditionMain = "cloudy",
                            weatherConditionId = 801,
                            weatherConditionDescription = "cloudy",
                        ),
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
                            lastUpdate = 3388339304494,
                            weatherConditionMain = "cloudy",
                            weatherConditionId = 801,
                            weatherConditionDescription = "cloudy",
                        ),
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
                            lastUpdate = 3388339304494,
                            weatherConditionMain = "cloudy",
                            weatherConditionId = 801,
                            weatherConditionDescription = "cloudy",
                        )
                    )
                )
            )
        }
    }

    @Test
    fun emptyResult() {
        runBlocking {
            doReturn(
                emptyList<Location>()
            ).`when`(mockWeatherRepository).getLocationWeatherFiveDayForecast(any(), any())

            val result =
                runUseCase(getLocationFiveDayForecast, GetLocationFiveDayForecast.Input(75.0, 89.0))
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
            ).`when`(mockWeatherRepository).getLocationWeatherFiveDayForecast(any(), any())

            val result =
                runUseCase(getLocationFiveDayForecast, GetLocationFiveDayForecast.Input(75.0, 89.0))
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
