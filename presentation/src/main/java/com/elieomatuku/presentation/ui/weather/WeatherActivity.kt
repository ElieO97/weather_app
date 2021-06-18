package com.elieomatuku.presentation.ui.weather

import android.os.Bundle
import android.view.MenuItem
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.elieomatuku.presentation.utils.Constants
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-18
 */

class WeatherActivity : BaseActivity(R.layout.activity_weather) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lat = intent.getDoubleExtra(Constants.LAT, 0.0)
        val long = intent.getDoubleExtra(Constants.LONG, 0.0)
        val name = intent.getStringExtra(Constants.LOCATION_NAME) ?: ""

        Timber.d("location name: $name")
        supportActionBar?.title = name
        supportActionBar?.setDisplayShowTitleEnabled(true)

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
