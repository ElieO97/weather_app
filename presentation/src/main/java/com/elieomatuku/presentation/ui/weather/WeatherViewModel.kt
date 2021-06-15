package com.elieomatuku.presentation.ui.weather

import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.interactor.weather.GetLocationCurrentWeather
import com.elieomatuku.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-14
 */

class WeatherViewModel(private val getLocationCurrentWeather: GetLocationCurrentWeather) :
    BaseViewModel<WeatherViewState>(WeatherViewState()) {

    fun getLocationCurrentWeather(lat: Double, long: Double) {
        GlobalScope.launch {
            getLocationCurrentWeather.execute(GetLocationCurrentWeather.Input(lat, long))
        }

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(getLocationCurrentWeather, GetLocationCurrentWeather.Input(lat, long))
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
    }
}