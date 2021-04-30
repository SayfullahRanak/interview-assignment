package com.sayfullah.utils.tools

import com.sayfullah.utils.models.CompareTimeModel
import java.util.concurrent.TimeUnit

class TimeUtils {

    companion object{

          fun compareTimes(
            startTimeSTR: String = System.currentTimeMillis().toString(),
            endTimeSTR: String
        ): CompareTimeModel? {


            val startTime = startTimeSTR.toLong()
            val endTime = endTimeSTR.toLong()
            if (endTime.length() != startTime.length())
                return null

            val diff = endTime - startTime

            val hours = TimeUnit.MILLISECONDS.toHours(diff)
            val days = TimeUnit.MILLISECONDS.toDays(diff)

            if (hours in 0..24) {
                return CompareTimeModel(
                    shortDurationTXT = "1 Day",
                    passedShortTXT = "$hours Hours",
                    durationNumber = 24.toString(),
                    durationFormat = "Hours",
                    passedNumber = hours.toString()
                )
            }

            if (hours in 24..720) {
                return CompareTimeModel(
                    shortDurationTXT = "1 Month",
                    passedShortTXT = "$days Days",
                    durationNumber = 30.toString(),
                    durationFormat = "Days",
                    passedNumber = days.toString()
                )
            }

            return null
        }

    }
}