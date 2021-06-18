package com.elieomatuku.domain.interactor.location

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.UseCase
import com.elieomatuku.domain.interactor.safeUseCaseCall
import com.elieomatuku.domain.model.Location
import com.elieomatuku.domain.repository.LocationRepository

/**
 * Created by elieomatuku on 2021-06-18
 */

class SearchLocation(private val locationRepository: LocationRepository) :
    UseCase<SearchLocation.Input, CompleteResult<List<Location>>> {

    override suspend fun execute(params: Input): CompleteResult<List<Location>> {
        return safeUseCaseCall {

            return@safeUseCaseCall locationRepository.searchLocation(
                params.name
            )
        }
    }

    data class Input(val name: String)
}
