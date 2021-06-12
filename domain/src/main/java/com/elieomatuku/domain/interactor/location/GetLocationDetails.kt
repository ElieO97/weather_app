package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.UseCase
import com.elieomatuku.domain.interactor.safeUseCaseCall
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-12
 */

class GetLocationDetails(private val locationRepository: LocationRepository) :
    UseCase<GetLocationDetails.Input, CompleteResult<Location>> {

    override suspend fun execute(params: Input): CompleteResult<Location> {
        return safeUseCaseCall {

            return@safeUseCaseCall locationRepository.getLocationDetails(
                params.latitude,
                params.longitude
            )
        }
    }

    data class Input(val latitude: Double, val longitude: Double)
}
