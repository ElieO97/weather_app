package com.elieomatuku.presentation.ui.weather

import android.location.Location
import android.os.Bundle
import android.view.View
import com.elieomatuku.presentation.extensions.hasLocationPermissions
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-16
 */

class CurrentLocationWeatherFragment : WeatherFragment() {
    companion object {
        fun newInstance(): WeatherFragment {
            return CurrentLocationWeatherFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (hasLocationPermissions()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    Timber.d("location: lat = ${location?.latitude}, long = ${location?.longitude} ")

                    location?.let {
                        viewModel.getLocationCurrentWeather(location.latitude, location.longitude)
                    }
                }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun refreshWeather() {
        if (hasLocationPermissions()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                Timber.d("location = ${location.longitude} ${location.latitude}")
                viewModel.getLocationCurrentWeather(location.latitude, location.longitude)
            }
        }
    }
}
