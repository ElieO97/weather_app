package com.elieomatuku.presentation.ui.weather.favouritelocations

import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.interactor.weather.GetFavouriteLocationCurrentWeather
import com.elieomatuku.domain.interactor.weather.GetLocationFiveDayForecast
import com.elieomatuku.presentation.ui.weather.BaseWeatherViewModel
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-19
 */

class FavouriteLocationWeatherViewModel(
    private val getFavouriteLocationCurrentWeather: GetFavouriteLocationCurrentWeather,
    getLocationFiveDayForecast: GetLocationFiveDayForecast
) : BaseWeatherViewModel(getLocationFiveDayForecast) {

    override fun getLocationCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(
                    getFavouriteLocationCurrentWeather,
                    GetFavouriteLocationCurrentWeather.Input(lat, long)
                )
            mapResult(result)
        }
        super.getLocationCurrentWeather(lat, long)
    }
}
