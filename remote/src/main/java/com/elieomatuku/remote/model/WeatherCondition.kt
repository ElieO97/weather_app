package com.elieomatuku.remote.model

import androidx.annotation.Keep
import com.elieomatuku.data.model.WeatherConditionEntity

/**
 * Created by elieomatuku on 2021-06-12
 */

@Keep
data class WeatherCondition(
    val id: Long?,
    val main: String?,
    val description: String?
) {

    companion object {
        fun toWeatherConditionEntity(weatherCondition: WeatherCondition): WeatherConditionEntity {
            return WeatherConditionEntity(
                weatherCondition.id?.toInt() ?: 0,
                weatherCondition.main ?: "",
                weatherCondition.description ?: ""

            )
        }
    }
}
