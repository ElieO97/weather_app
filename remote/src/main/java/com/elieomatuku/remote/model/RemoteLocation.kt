package com.elieomatuku.remote.model

import androidx.annotation.Keep

/**
 * Created by elieomatuku on 2021-06-12
 */

@Keep
data class RemoteLocation(
    val name: String?,
    val coord: Coordinates?,
    val country: String?
)
