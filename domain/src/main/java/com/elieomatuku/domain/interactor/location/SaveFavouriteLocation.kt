package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.NoOutputUseCase
import com.elieomatuku.domain.interactor.safeInteractorCall
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-12
 */

class SaveFavouriteLocation(private val locationRepository: LocationRepository) :
    NoOutputUseCase<Location> {
    override suspend fun execute(params: Location) {
        safeInteractorCall {

            return@safeInteractorCall locationRepository.saveFavouriteLocation(
                params
            )
        }
    }
}
