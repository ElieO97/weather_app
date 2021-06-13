package com.elieomatuku.mappers

import com.elieomatuku.data.model.WeatherConditionEntity
import com.elieomatuku.domain.model.WeatherCondition
import junit.framework.Assert
import org.junit.Test

/**
 * Created by elieomatuku on 2021-06-13
 */

class WeatherConditionEntityTest {

    var weatherCondition = WeatherConditionEntity(800, "sunny", "sunny")

    @Test
    fun sunnyMapperTest() {
        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Sunny)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Cloudy
        )
    }

    @Test
    fun rainyMapperTest() {
        weatherCondition = weatherCondition.copy(id = 501)

        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Rainy)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Sunny
        )

        weatherCondition = weatherCondition.copy(id = 502)

        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Rainy)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Sunny
        )
    }

    @Test
    fun cloudyMapperTest() {
        weatherCondition = weatherCondition.copy(id = 801)

        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Cloudy)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Sunny
        )

        weatherCondition = weatherCondition.copy(id = 802)

        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Cloudy)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Sunny
        )

        weatherCondition = weatherCondition.copy(id = 803)

        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Cloudy)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Sunny
        )

        weatherCondition = weatherCondition.copy(id = 804)

        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Cloudy)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Sunny
        )
    }

    @Test
    fun unknownMapperTest() {
        weatherCondition = weatherCondition.copy(id = 1000)

        Assert.assertEquals(weatherCondition.let(WeatherConditionEntity::toWeatherCondition), WeatherCondition.Unknown)
        Assert.assertNotSame(
            weatherCondition.let(WeatherConditionEntity::toWeatherCondition),
            WeatherCondition.Cloudy
        )
    }
}
