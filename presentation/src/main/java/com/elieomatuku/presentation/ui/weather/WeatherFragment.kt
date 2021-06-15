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
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }

    private val viewModel: WeatherViewModel by viewModel<WeatherViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLocationCurrentWeather(-33.9285191, 18.4312705)

        viewModel.viewState.observe(viewLifecycleOwner) {

            val weather = it.weather
            if (weather != null) {

                temperatureTv.text = "${weather.temperature}\u00B0"

                val backgroundRes = when (weather.weatherCondition) {
                    WeatherCondition.Sunny -> R.mipmap.forest_sunny
                    WeatherCondition.Cloudy -> R.mipmap.forest_cloudy
                    WeatherCondition.Rainy -> R.mipmap.forest_rainy
                    else -> R.mipmap.forest_sunny
                }

                headerContainer.setBackgroundResource(backgroundRes)
            }
        }
    }
}
