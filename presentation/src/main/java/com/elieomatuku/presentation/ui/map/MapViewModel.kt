package com.elieomatuku.presentation.ui.map

import androidx.lifecycle.viewModelScope
import com.elieomatuku.domain.interactor.Fail
import com.elieomatuku.domain.interactor.Success
import com.elieomatuku.domain.interactor.location.GetAllLocations
import com.elieomatuku.domain.interactor.runUseCase
import com.elieomatuku.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by elieomatuku on 2021-06-19
 */

class MapViewModel(private val getAllLocations: GetAllLocations) :
    BaseViewModel<MapViewState>(MapViewState()) {

    init {
        getAllLocations()
    }

    private fun getAllLocations() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                runUseCase(getAllLocations, Unit)
            state = when (result) {
                is Success -> state.copy(
                    isLoading = false,
                    locations = result.data
                )

                is Fail -> state.copy(
                    isLoading = false
                )
                else -> MapViewState()
            }
        }
    }
}
