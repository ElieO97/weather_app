package com.elieomatuku.weather_app

import android.app.Application
import org.kodein.di.Kodein
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-13
 */

class App : Application() {

    companion object {
        lateinit var kodein: Kodein
            private set
    }

    override fun onCreate() {
        super.onCreate()

        kodein = depInject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
