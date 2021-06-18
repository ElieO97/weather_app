package com.elieomatuku.presentation.ui.weather

import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.interactor.weather.GetCurrentLocationCurrentWeather
import com.elieomatuku.domain.interactor.weather.GetFavouriteLocationCurrentWeather
import com.elieomatuku.domain.interactor.weather.GetLocationFiveDayForecast
import com.elieomatuku.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-14
 */

class WeatherViewModel(
    private val getFavouriteLocationCurrentWeather: GetFavouriteLocationCurrentWeather,
    private val getCurrentLocationCurrentWeather: GetCurrentLocationCurrentWeather,
    private val getLocationFiveDayForecast: GetLocationFiveDayForecast
) :
    BaseViewModel<WeatherViewState>(WeatherViewState()) {

    fun getFavouriteLocationCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(
                    getFavouriteLocationCurrentWeather,
                    GetFavouriteLocationCurrentWeather.Input(lat, long)
                )
            state = when (result) {
                is Success -> state.copy(
                    isLoading = false,
                    weather = result.data
                )

                is Fail -> state.copy(
                    isLoading = false
                )
                else -> WeatherViewState()
            }
        }
        getLocationFiveDayForecast(lat, long)
    }

    fun getCurrentLocationCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(
                    getCurrentLocationCurrentWeather,
                    GetCurrentLocationCurrentWeather.Input(lat, long)
                )
            state = when (result) {
                is Success -> state.copy(
                    isLoading = false,
                    weather = result.data
                )

                is Fail -> state.copy(
                    isLoading = false
                )
                else -> WeatherViewState()
            }
        }
        getLocationFiveDayForecast(lat, long)
    }

    private fun getLocationFiveDayForecast(lat: Double, long: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(getLocationFiveDayForecast, GetLocationFiveDayForecast.Input(lat, long))
            state = when (result) {
                is Success -> state.copy(
                    isLoading = false,
                    forecast = result.data
                )

                is Fail -> state.copy(
                    isLoading = false
                )
                else -> WeatherViewState()
            }
        }
    }
}
