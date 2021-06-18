package com.elieomatuku.data.model

import com.elieomatuku.domain.model.WeatherCondition

/**
 * Created by elieomatuku on 2021-06-12
 */

data class WeatherConditionEntity(
    val id: Int,
    val main: String,
    val description: String,
) {

    companion object {
        fun toWeatherCondition(weatherConditionEntity: WeatherConditionEntity): WeatherCondition {
            return when (weatherConditionEntity.id) {
                800 -> WeatherCondition.Sunny
                in 801..804, 741 -> WeatherCondition.Cloudy
                in 500..531 -> WeatherCondition.Rainy
                else -> WeatherCondition.Unknown
            }
        }
    }
}
