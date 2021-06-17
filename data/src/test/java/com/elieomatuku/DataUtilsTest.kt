package com.elieomatuku

import com.elieomatuku.data.DataUtils
import junit.framework.Assert
import org.junit.Test

/**
 * Created by elieomatuku on 2021-06-17
 */

class DataUtilsTest {
    @Test
    fun convertUnixTimeToWeekDay() {
        Assert.assertEquals(DataUtils.convertUnixTimeToWeekDay(162429845), "Monday")
        Assert.assertEquals(DataUtils.convertUnixTimeToWeekDay(1623931245), "Thursday")
    }
}
