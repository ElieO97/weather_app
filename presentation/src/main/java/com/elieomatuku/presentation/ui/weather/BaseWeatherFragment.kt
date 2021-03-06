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
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.extensions.descriptionResources
import com.elieomatuku.presentation.extensions.getBackgroundColorResources
import com.elieomatuku.presentation.extensions.getBackgroundResources
import com.elieomatuku.presentation.ui.base.BaseFragment
import com.elieomatuku.presentation.utils.UiUtils.getDegreeAnnotation
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlin.properties.Delegates

/**
 * Created by elieomatuku on 2021-06-14
 */

abstract class BaseWeatherFragment : BaseFragment(R.layout.fragment_weather) {

    abstract val viewModel: BaseWeatherViewModel

    protected var long by Delegates.notNull<Double>()
    protected var lat by Delegates.notNull<Double>()

    private val forecastRv: RecyclerView by lazy {
        val view = forecastRV
        view.layoutManager = LinearLayoutManager(requireContext())
        view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner) { it ->

            refreshLayout.isRefreshing = false
            progressBar.isVisible = it.isLoading

            val weather = it.weather
            if (weather != null) {

                temperatureTv.text = getDegreeAnnotation(weather.temperature)

                temperatureLayout.isVisible = true
                separatorView.isVisible = true
                minTv.text = getDegreeAnnotation(weather.minimumTemperature)
                maxTv.text = getDegreeAnnotation(weather.maximumTemperature)
                currentTv.text = getDegreeAnnotation(weather.temperature)

                headerContainer.setBackgroundResource(weather.getBackgroundResources())
                weatherConditionTv.text =
                    weather.descriptionResources()?.let { res -> getString(res) }
                    ?: weather.weatherConditionMain
                rootView.setBackgroundResource(weather.getBackgroundColorResources())
                changeStatusAndActionBarColor(weather.getBackgroundColorResources())
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
