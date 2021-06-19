package com.elieomatuku.domain.interactor.weather

import com.elieomatuku.domain.interactor.CompleteResult
import com.elieomatuku.domain.interactor.UseCase
import com.elieomatuku.domain.interactor.safeUseCaseCall
import com.elieomatuku.domain.model.Weather
import com.elieomatuku.domain.repository.WeatherRepository

/**
 * Created by elieomatuku on 2021-06-12
 */

class GetFavouriteLocationCurrentWeather(private val weatherRepository: WeatherRepository) :
    UseCase<GetFavouriteLocationCurrentWeather.Input, CompleteResult<Weather>> {

    override suspend fun execute(params: Input): CompleteResult<Weather> {
        return safeUseCaseCall {
            val weather = weatherRepository.getLocationCurrentWeather(
                params.latitude,
                params.longitude
            )

            return@safeUseCaseCall weatherRepository.saveFavouriteLocationWeather(
                weather,
                params.latitude,
                params.longitude
            )
        }
    }

    data class Input(val latitude: Double, val longitude: Double)
}
