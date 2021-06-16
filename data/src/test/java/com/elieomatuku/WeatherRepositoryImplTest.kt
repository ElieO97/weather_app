package com.elieomatuku

import com.elieomatuku.data.WeatherRepositoryImpl
import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.model.WeatherConditionEntity
import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.data.repository.weather.WeatherCache
import com.elieomatuku.data.repository.weather.WeatherRemote
import com.elieomatuku.data.source.weather.WeatherCacheDataStore
import com.elieomatuku.data.source.weather.WeatherDataStoreFactory
import com.elieomatuku.data.source.weather.WeatherRemoteDataStore
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.model.WeatherCondition
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

class WeatherRepositoryImplTest {

    lateinit var weatherRepositoryImpl: WeatherRepositoryImpl
    lateinit var factory: WeatherDataStoreFactory

    private lateinit var weatherCacheDataStore: WeatherCacheDataStore
    private lateinit var weatherRemoteDataStore: WeatherRemoteDataStore

    lateinit var mockWeatherCache: WeatherCache
    lateinit var mockWeatherRemote: WeatherRemote

    @Before
    fun setUp() {

        mockWeatherCache = mock<WeatherCache>()
        mockWeatherRemote = mock<WeatherRemote>()

        weatherCacheDataStore = WeatherCacheDataStore(mockWeatherCache)
        weatherRemoteDataStore = WeatherRemoteDataStore(mockWeatherRemote)

        factory = WeatherDataStoreFactory(
            mockWeatherCache,
            weatherCacheDataStore,
            weatherRemoteDataStore
        )
        weatherRepositoryImpl = WeatherRepositoryImpl(factory)
    }

    @Test
    fun getCurrentLocationFromCache() {
        runBlocking {
            doReturn(
                true
            ).`when`(mockWeatherCache).isCached(any(), any())

            doReturn(
                false
            ).`when`(mockWeatherCache).isExpired(any(), any())

            doReturn(
                WeatherEntity(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherConditionEntity = WeatherConditionEntity(800, "sunny", "sunny"),
                    location = LocationEntity(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdatedInMilliseconds = 3388339304494
                )

            ).`when`(mockWeatherCache).getLocationCurrentWeather(any(), any())

            doReturn(
                WeatherEntity(
                    temperature = 29.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 35.0,
                    weatherConditionEntity = WeatherConditionEntity(800, "sunny", "sunny"),
                    location = LocationEntity(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdatedInMilliseconds = 3388339304494
                )

            ).`when`(mockWeatherRemote).getLocationCurrentWeather(any(), any())

            val result = weatherRepositoryImpl.getLocationCurrentWeather(23.0, 45.0)

            expect(result).toBe(
                Weather(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherCondition = WeatherCondition.Sunny,
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
    }

    @Test
    fun getCurrentLocationFromRemote() {
        runBlocking {
            doReturn(
                false
            ).`when`(mockWeatherCache).isCached(any(), any())

            doReturn(
                true
            ).`when`(mockWeatherCache).isExpired(any(), any())

            doReturn(
                WeatherEntity(
                    temperature = 21.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 25.0,
                    weatherConditionEntity = WeatherConditionEntity(800, "sunny", "sunny"),
                    location = LocationEntity(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdatedInMilliseconds = 3388339304494
                )

            ).`when`(mockWeatherCache).getLocationCurrentWeather(any(), any())

            doReturn(
                WeatherEntity(
                    temperature = 29.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 35.0,
                    weatherConditionEntity = WeatherConditionEntity(800, "sunny", "sunny"),
                    location = LocationEntity(
                        "Cape Town",
                        75.0,
                        89.0
                    ),
                    date = 1245955959,
                    lastUpdatedInMilliseconds = 3388339304494
                )

            ).`when`(mockWeatherRemote).getLocationCurrentWeather(any(), any())

            val result = weatherRepositoryImpl.getLocationCurrentWeather(23.0, 45.0)

            expect(result).toBe(
                Weather(
                    temperature = 29.0,
                    minimumTemperature = 16.0,
                    maximumTemperature = 35.0,
                    weatherCondition = WeatherCondition.Sunny,
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
    }
}
