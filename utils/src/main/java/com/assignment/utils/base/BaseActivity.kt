package com.assignment.utils.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("Registered")

open class BaseActivity(var isDialog: Boolean, var isFullScreen: Boolean ) :
    AppCompatActivity() {

    private lateinit var progress: ProgressDialog
    lateinit var mContext: Context
    lateinit var mActivity: Activity
    @SuppressLint("NewApi", "ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mActivity = this
        val window = window

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.BLACK
        }

        if (isFullScreen) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            supportActionBar?.hide()
        }
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please waiting for loading")
        //checkAccess()

        if (isDialog) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }


    }


    fun loading(show: Boolean) {
        if (show)
            progress.show()
        else
            progress.dismiss()
    }


}