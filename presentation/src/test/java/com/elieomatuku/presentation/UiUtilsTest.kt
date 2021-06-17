package com.elieomatuku.presentation

import com.elieomatuku.presentation.utils.UiUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class UiUtilsTest {
    @Test
    fun getDegreeAnnotation() {

        assertEquals(UiUtils.getDegreeAnnotation(16.7), "17\u00B0")
        assertEquals(UiUtils.getDegreeAnnotation(23.1), "23\u00B0")
        assertEquals(UiUtils.getDegreeAnnotation(12.5), "13\u00B0")
        assertEquals(UiUtils.getDegreeAnnotation(29.7), "30\u00B0")
        assertEquals(UiUtils.getDegreeAnnotation(30.1), "30\u00B0")
    }
}
