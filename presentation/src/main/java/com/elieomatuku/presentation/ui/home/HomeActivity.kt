package com.elieomatuku.presentation.ui.home

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.elieomatuku.presentation.ui.weather.WeatherFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-13
 */

class HomeActivity : BaseActivity(R.layout.activity_home) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.navHostContainer, WeatherFragment.newInstance())
                .commit()
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
                98
            )
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
        ) {

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    Timber.d("location: lat = ${location?.latitude}, long = ${location?.longitude} ")
                }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(navView)) {
                    drawerLayout.close()
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
