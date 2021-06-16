package com.elieomatuku.presentation.ui.weather

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.WeatherCondition
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlin.properties.Delegates

/**
 * Created by elieomatuku on 2021-06-14
 */

open class WeatherFragment : BaseFragment(R.layout.fragment_weather) {
    companion object {
        private const val LONG = "long"
        private const val LAT = "lat"

        fun newInstance(lat: Double, long: Double): WeatherFragment {
            val fragment = WeatherFragment()
            val args = Bundle()
            args.putDouble(LAT, lat)
            args.putDouble(LONG, long)

            fragment.arguments = args
            return fragment
        }
    }

    protected val viewModel: WeatherViewModel by viewModel<WeatherViewModel>()
    private var long by Delegates.notNull<Double>()
    private var lat by Delegates.notNull<Double>()

    private val forecastRv: RecyclerView by lazy {
        val view = forecastRV
        view.layoutManager = LinearLayoutManager(requireContext())
        view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner) {

            refreshLayout.isRefreshing = false
            progressBar.isVisible = it.isLoading

            val weather = it.weather
            if (weather != null) {

                temperatureTv.text = getDegreeAnnotation(weather.temperature.toInt())

                temperatureLayout.isVisible = true
                separatorView.isVisible = true
                minTv.text = getDegreeAnnotation(weather.minimumTemperature.toInt())
                maxTv.text = getDegreeAnnotation(weather.maximumTemperature.toInt())
                currentTv.text = getDegreeAnnotation(weather.temperature.toInt())

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
                changeStatusAndActionBarColor(backgroundColorRes)
            } else {
                temperatureLayout.isVisible = false
                separatorView.isVisible = false
            }

            forecastRv.adapter = ForecastAdapter(it.forecast)
        }

        refreshLayout.setOnRefreshListener {
            refreshWeather()
        }
    }

    open fun refreshWeather() {
        viewModel.getLocationCurrentWeather(lat, long)
    }

    private fun getDegreeAnnotation(value: Int): String {
        return "${value}\u00B0"
    }

    open fun changeStatusAndActionBarColor(resColor: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor = ContextCompat.getColor(
                requireContext(),
                resColor
            )
        }

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        requireContext(),
                        resColor
                    )
                )
            )
        }
    }
}
