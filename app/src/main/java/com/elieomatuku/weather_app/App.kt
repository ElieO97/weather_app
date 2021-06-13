package com.elieomatuku.weather_app

import android.app.Application
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-13
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
