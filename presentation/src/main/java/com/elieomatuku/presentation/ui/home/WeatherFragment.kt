package com.elieomatuku.presentation.ui.home

import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseFragment

/**
 * Created by elieomatuku on 2021-06-14
 */

class WeatherFragment : BaseFragment(R.layout.fragment_weather) {
    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }
}
