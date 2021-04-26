package com.assignment.zalora.utils

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
        fun decodeFile(filePath: String?, WIDTH: Int, HIGHT: Int): Bitmap? {
            try {
                val f = File(filePath)
                val o: BitmapFactory.Options = BitmapFactory.Options()
                o.inJustDecodeBounds = true
                BitmapFactory.decodeStream(FileInputStream(f), null, o)
                var scale = 1
                while (o.outWidth / scale / 2 >= WIDTH
                    && o.outHeight / scale / 2 >= HIGHT
                ) scale *= 2
                val o2: BitmapFactory.Options = BitmapFactory.Options()
                o2.inSampleSize = scale
                return BitmapFactory.decodeStream(FileInputStream(f), null, o2)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            return null
        }


    }

}
