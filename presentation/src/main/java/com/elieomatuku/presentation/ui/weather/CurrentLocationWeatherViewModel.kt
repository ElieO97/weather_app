package com.elieomatuku.presentation.ui.weather

import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.interactor.weather.GetCurrentLocationCurrentWeather
import com.elieomatuku.domain.interactor.weather.GetLocationFiveDayForecast
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-19
 */

class CurrentLocationWeatherViewModel(
    private val getCurrentLocationCurrentWeather: GetCurrentLocationCurrentWeather,
    getLocationFiveDayForecast: GetLocationFiveDayForecast
) : BaseWeatherViewModel(getLocationFiveDayForecast) {

    override fun getLocationCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(
                    getCurrentLocationCurrentWeather,
                    GetCurrentLocationCurrentWeather.Input(lat, long)
                )
            mapResult(result)
        }
        super.getLocationCurrentWeather(lat, long)
    }
}
