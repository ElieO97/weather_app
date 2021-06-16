package com.elieomatuku.remote.model

import androidx.annotation.Keep

/**
 * Created by elieomatuku on 2021-06-13
 */

@Keep
data class RemoteForecast(val list: List<RemoteWeather>, val city: RemoteLocation)
