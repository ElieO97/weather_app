package com.elieomatuku.mappers

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.model.WeatherConditionEntity
import com.elieomatuku.data.model.WeatherEntity
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.model.WeatherCondition
import junit.framework.Assert
import org.junit.Test

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherEntityTest {

    @Test
    fun mapperTest() {

        val weatherEntity =
            WeatherEntity(
                21.0,
                16.0,
                25.0,
                location = LocationEntity("Cape Town", 80.0, 73.0),
                date = 123484,
                weatherConditionEntity = WeatherConditionEntity(800, "sunny", "sunny"),
                lastUpdatedInMilliseconds = 13994449
            )
        val weather = Weather(
            21.0,
            16.0,
            25.0,
            location = Location("Cape Town", 80.0, 73.0),
            date = 123484,
            weatherCondition = WeatherCondition.Sunny,
            weatherConditionId = 800,
            weatherConditionMain = "sunny",
            weatherConditionDescription = "sunny",
            lastUpdate = 13994449
        )

        Assert.assertEquals(weatherEntity.let(WeatherEntity::toWeather), weather)
    }
}
