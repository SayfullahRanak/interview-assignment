package com.assignment.zalora.utils

import android.content.Context
import java.util.*
import javax.inject.Inject

class AppUtils @Inject constructor(
    private val context: Context
) {
    companion object {

        fun checkTime(time: String, currentTimeFromServer: Long): Boolean {


            for (timeOption in getTimingList(time)){

                try {
                    // timeOption = Mon - Tue,sun 11:00 am-12:00 am
//                    if(timeOption.contains(",".toRegex())){
//                        val timesWithComa = timeOption.split(" ".toRegex(), 2).toTypedArray()[1]
//
//                    }else{
//
//                    }


                    val times = timeOption.split(" ".toRegex(), 2).toTypedArray()[1]
                    //times = 11:00 am - 12:00 am

                    val timeWeekRange = timeOption.split(" ".toRegex()).toTypedArray()[0]
                    //timeWeekRange = Mon - Sun

                    val timeFromWeekDay = timeWeekRange.split("-".toRegex()).toTypedArray()[0].trim()
                    //timeFromWeekDay = Mon

                    val timeFromWithS = times.split("-".toRegex()).toTypedArray()[0]
                    //timeFromWithS = 11:00 am
                    val timeFromHourMinute = timeFromWithS.split(" ".toRegex()).toTypedArray()[0]
                    // timeFromHourMinute = 11:00

                    val timeFromHour = timeFromHourMinute.split(":".toRegex()).toTypedArray()[0]
                    // timeFromHour = 11
                    val timeFromMinute = timeFromHourMinute.split(":".toRegex()).toTypedArray()[1]
                    // timeFromMinute = 00

                    val timeFromStatus = timeFromWithS.split(" ".toRegex()).toTypedArray()[1]
                    //timeFromStatus = am


                    val timeUntilWeekDay = if(timeWeekRange.length > 1)  timeWeekRange.split("-".toRegex()).toTypedArray()[1].trim() else timeWeekRange.split("-".toRegex()).toTypedArray()[0].trim()
                    //timeUntilWeekDay = Tue

                    val timeUntilWithS = times.split("-".toRegex()).toTypedArray()[1]
                    //timeUntil = 12:00 am
                    val timeUntilHourMinute = timeFromWithS.split(" ".toRegex()).toTypedArray()[0]
                    // timeUntilHourMinute = 12:00

                    val timeUntilHour = timeUntilHourMinute.split(":".toRegex()).toTypedArray()[0]
                    // timeUntilHour = 12
                    val timeUntilMinute = timeUntilHourMinute.split(":".toRegex()).toTypedArray()[1]
                    // timeUntilHour = 00
                    val timeUntilStatus = timeFromWithS.split(" ".toRegex()).toTypedArray()[1]
                    //timeFromStatus = am

                    val fromTime = Calendar.getInstance()
                    fromTime.set(Calendar.HOUR, Integer.valueOf(timeFromHour))
                    fromTime.set(Calendar.MINUTE, Integer.valueOf(timeFromMinute))
                    fromTime.set(
                        Calendar.AM_PM,
                        if (timeFromStatus.trim().equals("am")) Calendar.AM else Calendar.PM
                    )
                    fromTime.set(Calendar.DAY_OF_WEEK, getWeekMap(timeFromWeekDay))

                    val toTime = Calendar.getInstance()
                    toTime.set(Calendar.HOUR, Integer.valueOf(timeUntilHour))
                    toTime.set(Calendar.MINUTE, Integer.valueOf(timeUntilMinute))
                    toTime.set(
                        Calendar.AM_PM,
                        if (timeUntilWithS.trim().equals("am")) Calendar.AM else Calendar.PM
                    )
                    toTime.set(Calendar.DAY_OF_WEEK, getWeekMap(timeUntilWeekDay))

                    val currentTime = Calendar.getInstance()
                    
                    if (currentTime.after(fromTime) && currentTime.before(toTime)) {
                        return true
                    }
                }catch (e: Exception) {
                    return false
                }
            }

            return false
        }


        fun getTimingList(time: String) : Array<String>{
            return time.split(" / ").toTypedArray()
        }

        fun getWeekMap(weekday: String):Int {
            when (weekday.toUpperCase()) {
                "SUN" -> {
                    return 1
                }
                "MON" -> {
                    return 2
                }
                "TUE" -> {
                    return 3
                }
                "WED" -> {
                    return 4
                }
                "THU" -> {
                    return 5
                }
                "FRI" -> {
                    return 6
                }
                "SAT" -> {
                    return 7
                }
            }
            return 0
        }

    }

}
