package com.elieomatuku.presentation.ui.home

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
    }
}
