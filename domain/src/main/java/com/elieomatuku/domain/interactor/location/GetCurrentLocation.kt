package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.UseCase
import com.elieomatuku.domain.interactor.safeInteractorCall
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-12
 */

class GetCurrentLocation(private val locationRepository: LocationRepository) :
    UseCase<GetCurrentLocation.Input, CompleteResult<Location>> {

    override suspend fun execute(params: Input): CompleteResult<Location> {
        return safeInteractorCall {

            return@safeInteractorCall locationRepository.getCurrentLocation(
                params.latitude,
                params.longitude
            )
        }
    }

    data class Input(val latitude: Double, val longitude: Double)
}
