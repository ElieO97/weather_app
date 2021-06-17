package com.elieomatuku.remote.model

import androidx.annotation.Keep
import com.elieomatuku.data.DataUtils.convertUnixTimeToWeekDay
import com.elieomatuku.data.DataUtils.today
import com.elieomatuku.data.model.WeatherEntity

/**
 * Created by elieomatuku on 2021-06-13
 */

@Keep
data class RemoteForecast(val list: List<RemoteWeather>, val city: RemoteLocation) {

    companion object {
        fun toWeatherEntities(remoteForecast: RemoteForecast): List<WeatherEntity> {
            return remoteForecast.list.map {
                RemoteWeather.toWeatherEntity(it, remoteForecast.city)
            }
        }

        fun filterMaxTempForecasts(remoteForecast: RemoteForecast): RemoteForecast {
            val weekDaysForecast = remoteForecast.list.map {
                val nuRemoteWeather = it.copy(dt_txt = convertUnixTimeToWeekDay(it.dt))
                nuRemoteWeather
            }

            return remoteForecast.copy(
                list = groupMaxWeatherForecast(weekDaysForecast).toMutableList()
                    .filter { it.dt_txt != today() }
            )
        }

        fun groupMaxWeatherForecast(weekDaysForecast: List<RemoteWeather>): List<RemoteWeather> {
            return mutableListOf(
                findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Monday"),
                findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Tuesday"),
                findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Wednesday"),
                findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Thursday"),
                findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Friday"),
                findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Saturday"),
                findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Sunday")
            ).filterNotNull()
        }

        fun findMaxTemperatureForecastForSpecificDay(
            weekDaysForecast: List<RemoteWeather>,
            day: String
        ): RemoteWeather? {
            val forecastsForDay: List<RemoteWeather> = weekDaysForecast.filter {
                it.dt_txt.equals(day, ignoreCase = true)
            }

            return if (!forecastsForDay.isNullOrEmpty()) {
                val maxTemp = forecastsForDay.maxOf {
                    it.main?.temp_max ?: 0.0
                }

                forecastsForDay.first { it.main?.temp_max == maxTemp }
            } else {
                null
            }
        }
    }
}
