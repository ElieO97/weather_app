package com.elieomatuku.presentation.ui.weather

import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
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

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            val isAccessFineLocationGranted: Boolean =
                permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val isAccessCoarseLocationGranted: Boolean =
                permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

            if (isAccessCoarseLocationGranted && isAccessFineLocationGranted) {
                refreshWeather()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (!hasLocationPermissions()) {
            requestMultiplePermissions.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
            )
        }

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
