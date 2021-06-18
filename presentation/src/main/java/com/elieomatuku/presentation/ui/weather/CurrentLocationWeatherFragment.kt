package com.elieomatuku.presentation.ui.weather

import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.elieomatuku.presentation.extensions.hasLocationPermissions
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-16
 */

class CurrentLocationWeatherFragment : BaseWeatherFragment() {
    companion object {
        private const val LOCATION_REQUEST_INTERVAL: Long = 10000

        fun newInstance(): BaseWeatherFragment {
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

    private val locationRequest: LocationRequest by lazy {
        LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(LOCATION_REQUEST_INTERVAL)
            .setFastestInterval(LOCATION_REQUEST_INTERVAL)
    }

    private val locationCallback: LocationCallback by lazy {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    refreshWeather()
                }
            }
        }
        locationCallback
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
            refreshWeather()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun refreshWeather() {
        if (hasLocationPermissions()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                Timber.d("location = ${location?.longitude} ${location?.latitude}")
                location?.let {
                    viewModel.getLocationCurrentWeather(location.latitude, location.longitude)
                } ?: startLocationUpdates()
            }
        }
    }

    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }
}
