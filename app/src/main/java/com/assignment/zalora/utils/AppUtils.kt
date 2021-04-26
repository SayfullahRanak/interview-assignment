package com.assignment.zalora.utils

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import javax.inject.Inject


class AppUtils @Inject constructor(
    private val context: Context
) {
    companion object {

        // Number of columns of Grid View
        const val NUM_OF_COLUMNS = 3

        // Gridview image padding
        const val GRID_PADDING = 8f // in dp

        fun getScreenHeight (activity: Activity) : Int{

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics =
                    activity.windowManager.currentWindowMetrics
                val insets: Insets = windowMetrics.windowInsets
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
                windowMetrics.bounds.width() - insets.left - insets.right
            } else {
                val displayMetrics = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
                displayMetrics.widthPixels
            }
        }

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

        /*
     * Resizing image size
     */

         fun scaleBitmap(bm: Bitmap): Bitmap? {
            var bm = bm
            var width = bm.width
            var height = bm.height

             val maxWidth = 100
             val maxHeight = 100

//            if (width > height) {
//                // landscape
//                val ratio = width.toFloat() / maxWidth
//                width = maxWidth
//                height = (height / ratio).toInt()
//            } else if (height > width) {
//                // portrait
//                val ratio = height.toFloat() / maxHeight
//                height = maxHeight
//                width = (width / ratio).toInt()
//            } else {
//                // square
//
//            }
             height = maxHeight
             width = maxWidth


            bm = Bitmap.createScaledBitmap(bm, width, height, true)
            return bm
        }


    }

}
