package com.elieomatuku.data

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by elieomatuku on 2021-06-17
 */

object DataUtils {
    fun convertUnixTimeToWeekDay(unixTime: Long?): String {
        val sdf = SimpleDateFormat("EEEE")
        val dateFormat = Date(unixTime?.times(1000) ?: 0)
        return sdf.format(dateFormat)
    }
}
