package com.elieomatuku.presentation.ui.weather

import android.os.Bundle
import android.view.View
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-18
 */

class WeatherFragment : BaseWeatherFragment() {
    companion object {
        private const val LONG = "long"
        private const val LAT = "lat"

        fun newInstance(lat: Double, long: Double): BaseWeatherFragment {
            val fragment = WeatherFragment()
            val args = Bundle()
            args.putDouble(LAT, lat)
            args.putDouble(LONG, long)

            fragment.arguments = args
            return fragment
        }
    }

    override val viewModel: BaseWeatherViewModel by viewModel<FavouriteLocationWeatherViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lat = arguments?.getDouble(LAT, 0.0) ?: 0.0
        long = arguments?.getDouble(LONG) ?: 0.0

        Timber.d("lat = $lat & long = $long")

        refreshWeather()
        super.onViewCreated(view, savedInstanceState)
    }
}
