package com.elieomatuku.data

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by elieomatuku on 2021-06-17
 */

object DataUtils {
    private val sdf = SimpleDateFormat("EEEE")

    fun convertUnixTimeToWeekDay(unixTime: Long?): String {
        val dateFormat = Date(unixTime?.times(1000) ?: 0)
        return sdf.format(dateFormat)
    }

    fun today(): String {
        val dateFormat = Date(System.currentTimeMillis())
        return sdf.format(dateFormat)
    }
}
