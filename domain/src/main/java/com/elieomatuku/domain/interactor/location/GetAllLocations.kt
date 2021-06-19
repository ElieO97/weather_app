package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.NoInputUseCase
import com.elieomatuku.domain.interactor.safeUseCaseCall
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-19
 */

class GetAllLocations(private val locationRepository: LocationRepository) :
    NoInputUseCase<CompleteResult<List<Location>>> {
    override suspend fun execute(params: Unit): CompleteResult<List<Location>> {
        return safeUseCaseCall {

            return@safeUseCaseCall locationRepository.getAllLocations()
        }
    }
}
