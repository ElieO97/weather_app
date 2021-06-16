package com.elieomatuku.presentation.ui.weather

import android.os.Bundle
import com.elieomatuku.presentation.extensions.hasLocationPermissions
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-16
 */

class CurrentLocationWeatherFragment : WeatherFragment() {
    companion object {
        const val LONG = "long"
        const val LAT = "lat"

        fun newInstance(lat: Double, long: Double): WeatherFragment {
            val fragment = CurrentLocationWeatherFragment()
            val args = Bundle()
            args.putDouble(LAT, lat)
            args.putDouble(LONG, long)

            fragment.arguments = args
            return fragment
        }
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
