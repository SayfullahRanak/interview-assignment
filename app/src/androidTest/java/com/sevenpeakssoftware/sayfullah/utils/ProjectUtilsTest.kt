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

   /* Dates should use next format:
    ● If within the current year: “[day] [month], [time]”. For example: “16 March, 12:34”
    ● If within a different year: “[day] [month] [year], [time]”. For example: “20 December 2017, 21:43”
    Time should follow system settings for 12h / 24h format, for example, “09:43 PM” vs “21:43”.*/
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
