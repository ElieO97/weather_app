package com.elieomatuku.presentation.ui.search

import com.elieomatuku.domain.model.Location

/**
 * Created by elieomatuku on 2021-06-18
 */

data class SearchViewState(
    val isLoading: Boolean = true,
    val searchResult: List<Location> = listOf(),
    val newFavouriteLocation: Boolean = false
)
