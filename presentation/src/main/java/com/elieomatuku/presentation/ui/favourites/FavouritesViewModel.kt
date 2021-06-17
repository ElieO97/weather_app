package com.elieomatuku.presentation.ui.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.location.SearchLocation
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-18
 */

class FavouritesViewModel(private val searchLocation: SearchLocation) :
    BaseViewModel<FavouritesViewState>(FavouritesViewState()) {

    val searchResultsData = MutableLiveData<List<Location>>(listOf())

    fun searchLocation(name: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(searchLocation, SearchLocation.Input(name))
            when (result) {
                is Success -> searchResultsData.value = result.data!!

                is Fail -> {
                }

                else -> {
                }
            }
            state = state.copy(isLoading = false)
        }
    }
}
