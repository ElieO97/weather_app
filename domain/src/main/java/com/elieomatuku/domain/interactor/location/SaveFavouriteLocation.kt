package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.UseCase
import com.elieomatuku.domain.interactor.safeUseCaseCall
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-12
 */

class SaveFavouriteLocation(private val locationRepository: LocationRepository) :
    UseCase<Location, CompleteResult<Unit>> {
    override suspend fun execute(params: Location): CompleteResult<Unit> {
        return safeUseCaseCall {

            return@safeUseCaseCall locationRepository.saveFavouriteLocation(
                params
            )
        }
    }
}
