package com.setel.assignment.utils

import android.app.Application
import com.github.tamir7.contacts.Contacts
import com.setel.assignment.di.components.DaggerApplicationComponent
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger.addLogAdapter
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)
        return component
    }

    companion object {
        lateinit var appInstance: Application
        lateinit var mobile:String
        lateinit var sessionID:String
        lateinit var username:String

    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        initModules()
    }


    private fun initModules() {
        Thread {
            addLogAdapter(AndroidLogAdapter())
            Hawk.init(this).build()
            Contacts.initialize(this)
        }.start()
    }


}