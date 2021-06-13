package com.elieomatuku.domain.interactor.weather

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.UseCase
import com.elieomatuku.domain.interactor.safeUseCaseCall
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.repository.WeatherRepository

/**
 * Created by elieomatuku on 2021-06-12
 */

class GetLocationFiveDayForecast(private val weatherRepository: WeatherRepository) :
    UseCase<GetLocationFiveDayForecast.Input, CompleteResult<List<Weather>>> {
    override suspend fun execute(params: Input): CompleteResult<List<Weather>> {
        return safeUseCaseCall {

            return@safeUseCaseCall weatherRepository.getLocationWeatherFiveDayForecast(
                params.latitude,
                params.longitude
            )
        }
    }

    data class Input(val latitude: Double, val longitude: Double)
}
