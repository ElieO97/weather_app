package com.elieomatuku.weather_app

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-13
 */

class App : Application(), KodeinAware {

    override val kodein: Kodein
        get() = depInject(this)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
