package com.elieomatuku.presentation

import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.model.WeatherCondition
import com.elieomatuku.presentation.extensions.descriptionResources
import com.elieomatuku.presentation.extensions.getBackgroundColorResources
import com.elieomatuku.presentation.extensions.getBackgroundResources
import com.elieomatuku.presentation.extensions.getSmallIconResources
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by elieomatuku on 2021-06-17
 */

class WeatherExtensionsTest {

    var weather = Weather(
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

    @Test
    fun getBackgroundResources() {
        assertEquals(weather.getBackgroundResources(), R.mipmap.forest_cloudy)

        weather = weather.copy(weatherCondition = WeatherCondition.Sunny)
        assertEquals(weather.getBackgroundResources(), R.mipmap.forest_sunny)

        weather = weather.copy(weatherCondition = WeatherCondition.Rainy)
        assertEquals(weather.getBackgroundResources(), R.mipmap.forest_rainy)

        weather = weather.copy(weatherCondition = WeatherCondition.Unknown)
        assertEquals(weather.getBackgroundResources(), R.color.teal_700)
    }

    @Test
    fun getSmallIconResources() {

        weather = weather.copy(weatherCondition = WeatherCondition.Cloudy)
        assertEquals(weather.getSmallIconResources(), R.mipmap.partlysunny)

        weather = weather.copy(weatherCondition = WeatherCondition.Sunny)
        assertEquals(weather.getSmallIconResources(), R.mipmap.clear)

        weather = weather.copy(weatherCondition = WeatherCondition.Rainy)
        assertEquals(weather.getSmallIconResources(), R.mipmap.rain)

        weather = weather.copy(weatherCondition = WeatherCondition.Unknown)
        assertEquals(weather.getSmallIconResources(), R.mipmap.clear)
    }

    @Test
    fun descriptionResources() {
        weather = weather.copy(weatherCondition = WeatherCondition.Cloudy)
        assertEquals(weather.descriptionResources(), R.string.cloudy)

        weather = weather.copy(weatherCondition = WeatherCondition.Sunny)
        assertEquals(weather.descriptionResources(), R.string.sunny)

        weather = weather.copy(weatherCondition = WeatherCondition.Rainy)
        assertEquals(weather.descriptionResources(), R.string.rainy)

        weather = weather.copy(weatherCondition = WeatherCondition.Unknown)
        assertEquals(weather.descriptionResources(), null)
    }

    @Test
    fun getBackgroundColorResources() {
        weather = weather.copy(weatherCondition = WeatherCondition.Cloudy)
        assertEquals(weather.getBackgroundColorResources(), R.color.cloudy)

        weather = weather.copy(weatherCondition = WeatherCondition.Sunny)
        assertEquals(weather.getBackgroundColorResources(), R.color.sunny)

        weather = weather.copy(weatherCondition = WeatherCondition.Rainy)
        assertEquals(weather.getBackgroundColorResources(), R.color.rainy)

        weather = weather.copy(weatherCondition = WeatherCondition.Unknown)
        assertEquals(weather.getBackgroundColorResources(), R.color.teal_700)
    }
}
