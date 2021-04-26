package com.assignment.zalora.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var appInstance: Application
    }
//
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
//
//
//    private fun initModules() {
//        Thread {
//            addLogAdapter(AndroidLogAdapter())
//            Hawk.init(this).build()
//            Contacts.initialize(this)
//        }.start()
//    }


}