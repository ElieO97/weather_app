package com.elieomatuku.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.elieomatuku.cache.location.CachedLocation
import com.elieomatuku.cache.location.LocationDao
import com.elieomatuku.cache.weather.CachedWeather
import com.elieomatuku.cache.weather.WeatherDao

/**
 * Created by elieomatuku on 2021-06-12
 */

@Database(entities = [CachedLocation::class, CachedWeather::class], version = 1)
abstract class WeatherAppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
    abstract fun locationDao(): LocationDao

    companion object {
        private const val DB_NAME = "weather_app_db"

        @Volatile
        var instance: WeatherAppDatabase? = null

        fun getInstance(context: Context): WeatherAppDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): WeatherAppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                WeatherAppDatabase::class.java, DB_NAME
            )
                .addCallback(object : Callback() {

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
