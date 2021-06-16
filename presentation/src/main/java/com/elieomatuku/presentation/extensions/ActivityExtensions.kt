package com.elieomatuku.presentation.extensions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

/**
 * Created by elieomatuku on 2021-06-16
 */

fun Activity.hasLocationPermissions(): Boolean {
    return (
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        )
}
