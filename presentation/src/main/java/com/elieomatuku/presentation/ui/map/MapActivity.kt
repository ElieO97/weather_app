package com.elieomatuku.presentation.ui.map

import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by elieomatuku on 2021-06-19
 */

class MapActivity : BaseActivity(R.layout.activity_map), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val viewModel: MapViewModel by viewModel<MapViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.map)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.viewState.observe(this) {
            it.locations?.let { locations ->
                if (locations.isNotEmpty()) {
                    if (mMap != null) {
                        mMap.clear()
                        locations.forEach { location ->
                            if (location.isCurrentLocation) {
                                markLocation(location)
                            } else {
                                markLocation(location, R.drawable.ic_baseline_location_on_24)
                            }
                        }
                    }
                }
            }
        }
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun markLocation(location: Location, favouriteRes: Int? = null) {
        val coordinates = LatLng(
            location.latitude,
            location.longitude
        )

        var markerOptions = MarkerOptions()
            .position(coordinates)

        if (favouriteRes != null) {
            val favouriteMarkerDrawable =
                ContextCompat.getDrawable(this, favouriteRes)

            markerOptions = markerOptions.icon(
                BitmapDescriptorFactory.fromBitmap(
                    favouriteMarkerDrawable?.toBitmap(
                        favouriteMarkerDrawable?.intrinsicWidth,
                        favouriteMarkerDrawable?.intrinsicHeight,
                        null
                    )
                )
            )
        }

        mMap.addMarker(
            markerOptions
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
    }
}
