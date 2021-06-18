package com.elieomatuku.presentation.ui.favourites

import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.location.DeleteFavouriteLocation
import com.elieomatuku.domain.interactor.location.GetFavouriteLocations
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-18
 */

class FavouritesViewModel(private val getFavouriteLocations: GetFavouriteLocations, private val deleteFavouriteLocation: DeleteFavouriteLocation) :
    BaseViewModel<FavouritesViewState>(FavouritesViewState()) {

    init {
        getFavouriteLocations()
    }

    fun getFavouriteLocations() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(getFavouriteLocations, Unit)
            state = when (result) {
                is Success -> state.copy(
                    isLoading = false,
                    favourites = result.data
                )

                is Fail -> state.copy(
                    isLoading = false
                )
                else -> FavouritesViewState()
            }
        }
    }

    fun deleteFavouriteLocation(location: Location) {
        viewModelScope.launch {
            runUseCase(deleteFavouriteLocation, location)
        }
    }
}
