package com.elieomatuku.presentation.injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.elieomatuku.domain.interactor.location.DeleteFavouriteLocation
import com.elieomatuku.domain.interactor.location.GetAllLocations
import com.elieomatuku.domain.interactor.location.GetCurrentLocation
import com.elieomatuku.domain.interactor.location.GetFavouriteLocations
import com.elieomatuku.domain.interactor.location.GetLocationDetails
import com.elieomatuku.domain.interactor.location.SaveFavouriteLocation
import com.elieomatuku.domain.interactor.location.SearchLocation
import com.elieomatuku.domain.interactor.weather.GetCurrentLocationCurrentWeather
import com.elieomatuku.domain.interactor.weather.GetFavouriteLocationCurrentWeather
import com.elieomatuku.domain.interactor.weather.GetLocationFiveDayForecast
import com.elieomatuku.presentation.ui.favourites.FavouritesViewModel
import com.elieomatuku.presentation.ui.map.MapViewModel
import com.elieomatuku.presentation.ui.search.SearchViewModel
import com.elieomatuku.presentation.ui.weather.currentlocation.CurrentLocationWeatherViewModel
import com.elieomatuku.presentation.ui.weather.favouritelocations.FavouriteLocationWeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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

            bind<GetFavouriteLocationCurrentWeather>() with singleton {
                GetFavouriteLocationCurrentWeather(instance())
            }

            bind<GetLocationFiveDayForecast>() with singleton {
                GetLocationFiveDayForecast(instance())
            }

            bind<GetCurrentLocationCurrentWeather>() with singleton {
                GetCurrentLocationCurrentWeather(instance())
            }

            bind<SearchLocation>() with singleton {
                SearchLocation(instance())
            }

            bind<DeleteFavouriteLocation>() with singleton {
                DeleteFavouriteLocation(instance())
            }

            bind<GetAllLocations>() with singleton {
                GetAllLocations(instance())
            }

            bindViewModel<CurrentLocationWeatherViewModel>() with provider {
                CurrentLocationWeatherViewModel(instance(), instance())
            }

            bindViewModel<FavouriteLocationWeatherViewModel>() with provider {
                FavouriteLocationWeatherViewModel(instance(), instance())
            }

            bindViewModel<FavouritesViewModel>() with provider {
                FavouritesViewModel(instance(), instance())
            }

            bindViewModel<SearchViewModel>() with provider {
                SearchViewModel(instance(), instance())
            }

            bindViewModel<MapViewModel>() with provider {
                MapViewModel(instance())
            }

            bind<FusedLocationProviderClient>() with singleton {
                val context: Context = instance()
                LocationServices.getFusedLocationProviderClient(context)
            }
        }
    }
}
