package com.elieomatuku.presentation.ui.favourites

import com.elieomatuku.domain.model.Location

/**
 * Created by elieomatuku on 2021-06-18
 */

data class FavouritesViewState(
    val isLoading: Boolean = false,
    val favourites: List<Location> = listOf()
)
