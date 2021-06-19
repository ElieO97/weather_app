package com.elieomatuku.presentation.ui.map

import com.elieomatuku.domain.model.Location

/**
 * Created by elieomatuku on 2021-06-19
 */

data class MapViewState(
    val isLoading: Boolean = false,
    val locations: List<Location>? = null
)
