package com.elieomatuku.presentation.ui.search

import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.location.SaveFavouriteLocation
import com.elieomatuku.domain.interactor.location.SearchLocation
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-18
 */

class SearchViewModel(
    private val searchLocation: SearchLocation,
    private val saveFavouriteLocation: SaveFavouriteLocation
) :
    BaseViewModel<SearchViewState>(SearchViewState()) {

    fun searchLocation(name: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(searchLocation, SearchLocation.Input(name))
            state = when (result) {
                is Success -> state.copy(
                    isLoading = false,
                    searchResult = result.data
                )

                is Fail -> state.copy(
                    isLoading = false
                )
                else -> SearchViewState()
            }
        }
    }

    fun searchFavouriteLocation(location: Location) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(saveFavouriteLocation, location)
            state = when (result) {
                is Success -> state.copy(
                    isLoading = false,
                    newFavouriteLocation = false
                )

                is Fail -> state.copy(
                    isLoading = false
                )
                else -> SearchViewState()
            }
        }
    }
}
