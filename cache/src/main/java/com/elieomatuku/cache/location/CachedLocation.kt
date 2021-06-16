package com.elieomatuku.cache.location

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by elieomatuku on 2021-06-12
 */

@Entity(tableName = CachedLocation.LOCATION_TABLE)
data class CachedLocation(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val currentLocation: Boolean? = null,
    val favouriteLocation: Boolean? = null
) {
    companion object {
        const val LOCATION_TABLE = "location_table"
    }
}
