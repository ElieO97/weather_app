package com.elieomatuku.remote.model

import androidx.annotation.Keep
import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.data.model.WeatherConditionEntity
import com.elieomatuku.data.model.WeatherEntity

/**
 * Created by elieomatuku on 2021-06-12
 */

@Keep
data class RemoteWeather(
    val weather: List<WeatherCondition?>,
    val main: WeatherMain?,
    val dt: Long?,
    val dt_txt: String?,
    val id: Long?,
    val name: String?,
    val coord: Coordinates?,
) {

    companion object {
        fun toWeatherEntity(remoteWeather: RemoteWeather): WeatherEntity {
            return WeatherEntity(
                temperature = remoteWeather.main?.temp ?: 0.0,
                minimumTemperature = remoteWeather.main?.temp_min ?: 0.0,
                maximumTemperature = remoteWeather.main?.temp_max ?: 0.0,
                location = LocationEntity(
                    name = remoteWeather.name ?: "",
                    longitude = remoteWeather.coord?.lon ?: 0.0,
                    latitude = remoteWeather.coord?.lat ?: 0.0,
                    id = remoteWeather.id
                ),
                date = remoteWeather.dt ?: 0,
                weatherConditionEntity = remoteWeather.weather.first()?.let(
                    WeatherCondition::toWeatherConditionEntity
                ) ?: WeatherConditionEntity(0, "", ""),
                lastUpdatedInMilliseconds = System.currentTimeMillis()
            )
        }

        fun toWeatherEntity(
            remoteWeather: RemoteWeather,
            remoteLocation: RemoteLocation
        ): WeatherEntity {
            return WeatherEntity(
                temperature = remoteWeather.main?.temp ?: 0.0,
                minimumTemperature = remoteWeather.main?.temp_min ?: 0.0,
                maximumTemperature = remoteWeather.main?.temp_max ?: 0.0,
                location = LocationEntity(
                    name = remoteLocation.name ?: "",
                    longitude = remoteLocation.coord?.lon ?: 0.0,
                    latitude = remoteLocation.coord?.lat ?: 0.0,
                    id = remoteLocation.id
                ),
                date = remoteWeather.dt ?: 0,
                weekDay = remoteWeather.dt_txt,
                weatherConditionEntity = remoteWeather.weather.first()?.let(
                    WeatherCondition::toWeatherConditionEntity
                ) ?: WeatherConditionEntity(0, "", ""),
                lastUpdatedInMilliseconds = System.currentTimeMillis()
            )
        }
    }
}
