package com.elieomatuku.presentation.ui.weather

import android.os.Bundle
import android.view.View
import com.elieomatuku.domain.model.WeatherCondition
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_weather.*

/**
 * Created by elieomatuku on 2021-06-14
 */

class WeatherFragment : BaseFragment(R.layout.fragment_weather) {
    companion object {
        const val LONG = "long"
        const val LAT = "lat"

        fun newInstance(lat: Double, long: Double): WeatherFragment {
            val fragment = WeatherFragment()
            val args = Bundle()
            args.putDouble(LAT, lat)
            args.putDouble(LONG, long)

            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: WeatherViewModel by viewModel<WeatherViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val long = arguments?.getDouble(LONG) ?: 0.0
        val lat = arguments?.getDouble(LAT) ?: 0.0

        viewModel.getLocationCurrentWeather(lat, long)

        viewModel.viewState.observe(viewLifecycleOwner) {

            val weather = it.weather
            if (weather != null) {

                temperatureTv.text = "${weather.temperature.toInt()}\u00B0"

                val backgroundRes = when (weather.weatherCondition) {
                    WeatherCondition.Sunny -> R.mipmap.forest_sunny
                    WeatherCondition.Cloudy -> R.mipmap.forest_cloudy
                    WeatherCondition.Rainy -> R.mipmap.forest_rainy
                    else -> R.mipmap.forest_sunny
                }

                headerContainer.setBackgroundResource(backgroundRes)

                val weatherConditionRes = when (weather.weatherCondition) {
                    WeatherCondition.Sunny -> R.string.sunny
                    WeatherCondition.Cloudy -> R.string.cloudy
                    WeatherCondition.Rainy -> R.string.rainy
                    else -> R.string.sunny
                }
                weatherConditionTv.text = getString(weatherConditionRes)

                val backgroundColorRes = when (weather.weatherCondition) {
                    WeatherCondition.Sunny -> R.color.sunny
                    WeatherCondition.Cloudy -> R.color.cloudy
                    WeatherCondition.Rainy -> R.color.rainy
                    else -> R.string.sunny
                }
                rootView.setBackgroundResource(backgroundColorRes)
            }
        }
    }
}
