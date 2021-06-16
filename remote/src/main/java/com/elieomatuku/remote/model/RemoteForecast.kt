package com.elieomatuku.remote.model

import androidx.annotation.Keep
import com.elieomatuku.data.model.WeatherEntity
import java.text.SimpleDateFormat
import java.util.Date

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
                list = groupMaxWeatherForecast(weekDaysForecast)
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

        fun convertUnixTimeToWeekDay(unixTime: Long?): String {
            val sdf = SimpleDateFormat("EEEE")
            val dateFormat = Date(unixTime?.times(1000) ?: 0)
            return sdf.format(dateFormat)
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
