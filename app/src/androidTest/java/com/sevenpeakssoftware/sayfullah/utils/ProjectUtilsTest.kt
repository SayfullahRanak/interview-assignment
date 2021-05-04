package com.sevenpeakssoftware.sayfullah.utils

import android.content.Context
import android.text.format.DateFormat
import org.junit.Assert
import org.junit.Before

import org.junit.Test

class ProjectUtilsTest {

    lateinit var context : Context

    @Before
    fun setUp() {

        context =
            MyApplication.appInstance
    }

    @Test
    fun whenFormatIsValid(){

        val beforeFormatDate = "25.05.2018 14:13"

        val afterFormatDate = if (!DateFormat.is24HourFormat(context)) "25 May 2018, 02:13 PM" else "25 May 2018, 14:13"

        Assert.assertEquals(true,
            ProjectUtils.timeFormatChange(
                context,
                beforeFormatDate
            ).equals(afterFormatDate))
    }
}
