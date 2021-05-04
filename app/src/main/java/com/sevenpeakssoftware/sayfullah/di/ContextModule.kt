package com.sevenpeakssoftware.sayfullah.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@Module
@InstallIn(ApplicationComponent::class)
class ContextModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context{
        return application.applicationContext
    }
}