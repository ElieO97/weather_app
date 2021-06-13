package com.elieomatuku.mappers

import com.elieomatuku.data.model.LocationEntity
import com.elieomatuku.domain.model.Location
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import org.junit.Test


/**
 * Created by elieomatuku on 2021-06-13
 */

class LocationEntityTest {
    @Test
    fun mapperTest() {
        val locationEntity = LocationEntity("Cape Town", 80.0, 73.0)
        val location = Location("Cape Town", 80.0, 73.0)

        assertEquals(locationEntity.let(LocationEntity::toLocation), location)
        assertNotSame(locationEntity.let(LocationEntity::toLocation), Location("Joburg", 80.0, 73.0))

    }
}