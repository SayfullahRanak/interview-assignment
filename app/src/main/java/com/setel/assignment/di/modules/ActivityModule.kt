package com.setel.assignment.di.modules

import com.setel.assignment.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.setel.assignment.ui.restaurants.RestaurantDetailActivity
import com.setel.assignment.ui.restaurants.RestaurantListActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun contributeRestaurantDetailActivity(): RestaurantDetailActivity

    @ContributesAndroidInjector
    internal abstract fun contributeRestaurantListActivity(): RestaurantListActivity

}