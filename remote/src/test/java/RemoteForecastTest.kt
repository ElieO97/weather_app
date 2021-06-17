import com.elieomatuku.data.DataUtils
import com.elieomatuku.remote.model.Coordinates
import com.elieomatuku.remote.model.RemoteForecast
import com.elieomatuku.remote.model.RemoteLocation
import com.elieomatuku.remote.model.RemoteWeather
import com.elieomatuku.remote.model.WeatherCondition
import com.elieomatuku.remote.model.WeatherMain
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by elieomatuku on 2021-06-16
 */

class RemoteForecastTest {

    private val forecast = RemoteForecast(
        list = listOf(
            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 234.0),
                dt = 1623931200,
                id = 1623931200,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = ""
            ),
            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 354.0),
                dt = 1623931245,
                id = 1623931245,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = ""
            ),
            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 304.0),
                dt = 1624298400,
                id = 1623931223,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = ""
            ),

            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 304.0),
                dt = 1624298455,
                id = 1623931223,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = ""
            ),

            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 404.0),
                dt = 1624298450,
                id = 1623931223,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = ""
            )
        ),
        city = RemoteLocation(
            "Cape Town",
            Coordinates(23.0, 234.0),
            "South Africa"
        )
    )

    private val maxForecast = RemoteForecast(
        list = listOf(
            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 404.0),
                dt = 1624298450,
                id = 1623931223,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = "Monday"
            ),
            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 354.0),
                dt = 1623931245,
                id = 1623931245,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = "Thursday"
            ),
        ),
        city = RemoteLocation(
            "Cape Town",
            Coordinates(23.0, 234.0),
            "South Africa"
        )
    )

    @Test
    fun filterMaxTempForecasts() {
        val list = maxForecast.list.toMutableList().filter { it.dt_txt != DataUtils.today() }
        assertEquals(RemoteForecast.filterMaxTempForecasts(forecast), maxForecast.copy(list = list))
    }

    @Test
    fun groupMaxTemperatureForecast() {
        val weekDaysForecast = forecast.list.map {
            val nuRemoteWeather = it.copy(dt_txt = DataUtils.convertUnixTimeToWeekDay(it.dt))
            nuRemoteWeather
        }

        assertEquals(
            RemoteForecast.groupMaxWeatherForecast(weekDaysForecast),
            listOf(
                RemoteWeather(
                    weather = listOf(
                        WeatherCondition(
                            id = 800,
                            main = null,
                            description = null
                        )
                    ),
                    main = WeatherMain(123.0, 123.0, 404.0),
                    dt = 1624298450,
                    id = 1623931223,
                    name = null,
                    coord = Coordinates(23.0, 234.0),
                    dt_txt = "Monday"
                ),
                RemoteWeather(
                    weather = listOf(
                        WeatherCondition(
                            id = 800,
                            main = null,
                            description = null
                        )
                    ),
                    main = WeatherMain(123.0, 123.0, 354.0),
                    dt = 1623931245,
                    id = 1623931245,
                    name = null,
                    coord = Coordinates(23.0, 234.0),
                    dt_txt = "Thursday"
                )
            )
        )
    }

    @Test
    fun findMaxTemperatureForecastForSpecificDay() {
        val weekDaysForecast = forecast.list.map {
            val nuRemoteWeather = it.copy(dt_txt = DataUtils.convertUnixTimeToWeekDay(it.dt))
            nuRemoteWeather
        }

        assertEquals(
            RemoteForecast.findMaxTemperatureForecastForSpecificDay(weekDaysForecast, "Monday"),
            RemoteWeather(
                weather = listOf(
                    WeatherCondition(
                        id = 800,
                        main = null,
                        description = null
                    )
                ),
                main = WeatherMain(123.0, 123.0, 404.0),
                dt = 1624298450,
                id = 1623931223,
                name = null,
                coord = Coordinates(23.0, 234.0),
                dt_txt = "Monday"
            )
        )
    }
}
