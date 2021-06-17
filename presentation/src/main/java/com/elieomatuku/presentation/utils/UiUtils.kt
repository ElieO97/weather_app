package com.elieomatuku.presentation.utils

import kotlin.math.roundToInt

/**
 * Created by elieomatuku on 2021-06-17
 */

object UiUtils {

    fun getDegreeAnnotation(value: Double): String {
        return "${value.roundToInt()}\u00B0"
    }
}
