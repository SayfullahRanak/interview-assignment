package com.assignment.assignment.utils

import android.app.Application
import com.github.tamir7.contacts.Contacts
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger.addLogAdapter
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

//    companion object {
//        lateinit var appInstance: Application
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        appInstance = this
//        initModules()
//    }
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