package com.elieomatuku.presentation.injection

import androidx.lifecycle.ViewModelProvider
import com.elieomatuku.domain.interactor.location.GetCurrentLocation
import com.elieomatuku.domain.interactor.location.GetFavouriteLocations
import com.elieomatuku.domain.interactor.location.GetLocationDetails
import com.elieomatuku.domain.interactor.location.SaveFavouriteLocation
import com.elieomatuku.domain.interactor.weather.GetLocationCurrentWeather
import com.elieomatuku.domain.interactor.weather.GetLocationFiveDayForecast
import com.elieomatuku.presentation.ui.home.WeatherViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by elieomatuku on 2021-06-13
 */

object PresentationKodeinModule {

    const val moduleName = "presentation"

    fun getModule(): Kodein.Module {

        return Kodein.Module(name = moduleName) {

            bind<ViewModelProvider.Factory>() with singleton { KodeinViewModelFactory(this.kodein) }

            bind<GetCurrentLocation>() with singleton {
                GetCurrentLocation(instance())
            }

            bind<GetFavouriteLocations>() with singleton {
                GetFavouriteLocations(instance())
            }

            bind<GetLocationDetails>() with singleton {
                GetLocationDetails(instance())
            }

            bind<SaveFavouriteLocation>() with singleton {
                SaveFavouriteLocation(instance())
            }

            bind<GetLocationCurrentWeather>() with singleton {
                GetLocationCurrentWeather(instance())
            }

            bind<GetLocationFiveDayForecast>() with singleton {
                GetLocationFiveDayForecast(instance())
            }

            bindViewModel<WeatherViewModel>() with provider {
                WeatherViewModel(instance())
            }
        }
    }
}
