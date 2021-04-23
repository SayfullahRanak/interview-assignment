package com.assignment.assignment.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ContextModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context{
        return application.applicationContext
    }
}