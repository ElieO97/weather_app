package com.elieomatuku.presentation.ui.favourites

import android.os.Bundle
import android.view.MenuItem
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.elieomatuku.presentation.ui.weather.WeatherFragment

/**
 * Created by elieomatuku on 2021-06-18
 */

class WeatherActivity : BaseActivity(R.layout.activity_weather) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lat = intent.getDoubleExtra("lat", 0.0)
        val long = intent.getDoubleExtra("long", 0.0)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.navHostContainer,
                WeatherFragment.newInstance(lat, long)
            )
            .commitAllowingStateLoss()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
