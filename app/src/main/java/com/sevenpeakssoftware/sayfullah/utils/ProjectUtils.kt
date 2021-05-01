package com.sevenpeakssoftware.sayfullah.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Insets
import android.os.Build
import android.text.format.DateFormat
import android.util.DisplayMetrics
import android.view.WindowInsets
import java.text.SimpleDateFormat
import java.util.*

class ProjectUtils {

    companion object{
        fun getScreenWidth(activity: Activity) : Int{
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics =
                        activity.windowManager.currentWindowMetrics
                val insets: Insets = windowMetrics.windowInsets
                        .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
                windowMetrics.bounds.height() - insets.top - insets.bottom
            } else {
                val displayMetrics = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
                displayMetrics.heightPixels
            }
        }


        @SuppressLint("SimpleDateFormat")
        fun timeFormateChange(dateTime : String) : String{

            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val cal = Calendar.getInstance()
            val parsedDate: Date? = formatter.parse(dateTime)

            cal.time = parsedDate!!

            val date = cal.get(Calendar.DATE)
            val month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.US)
            val year = if(cal.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) "" else " ${cal.get(Calendar.YEAR)}"

            var am_pm = ""

            if (!DateFormat.is24HourFormat(MyApplication.appInstance)) {
                am_pm = if(cal.get(Calendar.AM_PM) == Calendar.AM) " AM" else " PM"
            }


            val hr = if (!DateFormat.is24HourFormat(MyApplication.appInstance)) cal.get(Calendar.HOUR) else cal.get(Calendar.HOUR_OF_DAY)

            val formattedHr = String.format("%02d", hr)

            val min = cal.get(Calendar.MINUTE)

            val formattedMin = String.format("%02d", min)


            val formatedDate = "$date $month$year, $formattedHr:$formattedMin$am_pm"
            return formatedDate
        }




    }
}