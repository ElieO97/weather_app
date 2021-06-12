package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.NoInputUseCase
import com.elieomatuku.domain.interactor.safeInteractorCall
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-12
 */

class GetFavouriteLocations(private val locationRepository: LocationRepository) :
    NoInputUseCase<CompleteResult<List<Location>>> {
    override suspend fun execute(params: Unit): CompleteResult<List<Location>> {
        return safeInteractorCall {

            return@safeInteractorCall locationRepository.getFavouritesLocations()
        }
    }
}
