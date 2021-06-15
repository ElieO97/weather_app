package com.elieomatuku.weather_app

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.elieomatuku.cache.LocationCacheImpl
import com.elieomatuku.cache.WeatherCacheImpl
import com.elieomatuku.data.LocationRepositoryImpl
import com.elieomatuku.data.WeatherRepositoryImpl
import com.elieomatuku.data.repository.location.LocationCache
import com.elieomatuku.data.repository.location.LocationRemote
import com.elieomatuku.data.repository.weather.WeatherCache
import com.elieomatuku.data.repository.weather.WeatherRemote
import com.elieomatuku.data.source.location.LocationCacheDataStore
import com.elieomatuku.data.source.location.LocationDataStoreFactory
import com.elieomatuku.data.source.location.LocationRemoteDataStore
import com.elieomatuku.data.source.weather.WeatherCacheDataStore
import com.elieomatuku.data.source.weather.WeatherDataStoreFactory
import com.elieomatuku.data.source.weather.WeatherRemoteDataStore
import com.elieomatuku.domain.repository.LocationRepository
import com.elieomatuku.domain.repository.WeatherRepository
import com.elieomatuku.presentation.injection.PresentationKodeinModule
import com.elieomatuku.remote.LocationRemoteImpl
import com.elieomatuku.remote.WeatherRemoteImpl
import com.elieomatuku.remote.api.LocationApi
import com.elieomatuku.remote.api.WeatherApi
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by elieomatuku on 2021-06-13
 */

fun depInject(app: Application): Kodein {

    return Kodein.lazy {
        import(androidXModule(app))
        bind<Context>() with instance(app.applicationContext)
        bind<Resources>() with instance(app.applicationContext.resources)
        bind<OkHttpClient>() with singleton {
            val clientBuilder = OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val original = chain.request()
                    val url = original.url.newBuilder()
                        .addQueryParameter("appid", BuildConfig.WEATHER_APIKEY)
                        .addEncodedQueryParameter("units", "metric")
                        .build()

                    val request = original.newBuilder()
                        .method(original.method, original.body)
                        .url(url)
                        .build()

                    chain.proceed(request)
                }
                .connectTimeout(45, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(0, 1, TimeUnit.MILLISECONDS))
                .writeTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logging = okhttp3.logging.HttpLoggingInterceptor()
                logging.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(logging)
            }

            clientBuilder.build()
        }

        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                .client(instance())
                .baseUrl(BuildConfig.WEATHER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        bind<LocationApi>() with singleton {
            val retrofit: Retrofit = instance()
            retrofit.create<LocationApi>(
                LocationApi::class.java
            )
        }

        bind<WeatherApi>() with singleton {
            val retrofit: Retrofit = instance()
            retrofit.create<WeatherApi>(
                WeatherApi::class.java
            )
        }

        bind<WeatherRemote>() with singleton {
            WeatherRemoteImpl(instance())
        }

        bind<WeatherCache>() with singleton {
            WeatherCacheImpl()
        }

        bind<LocationCache>() with singleton {
            LocationCacheImpl()
        }

        bind<LocationRemote>() with singleton {
            LocationRemoteImpl(instance())
        }

        bind<LocationCacheDataStore>() with singleton {
            LocationCacheDataStore(instance())
        }

        bind<LocationRemoteDataStore>() with singleton {
            LocationRemoteDataStore(instance())
        }

        bind<LocationDataStoreFactory>() with singleton {
            LocationDataStoreFactory(instance(), instance(), instance())
        }

        bind<WeatherCacheDataStore>() with singleton {
            WeatherCacheDataStore(instance())
        }

        bind<WeatherRemoteDataStore>() with singleton {
            WeatherRemoteDataStore(instance())
        }

        bind<WeatherDataStoreFactory>() with singleton {
            WeatherDataStoreFactory(instance(), instance(), instance())
        }

        bind<LocationRepository>() with singleton {
            LocationRepositoryImpl(instance())
        }

        bind<WeatherRepository>() with singleton {
            WeatherRepositoryImpl(instance())
        }

        importOnce(PresentationKodeinModule.getModule())
    }
}
