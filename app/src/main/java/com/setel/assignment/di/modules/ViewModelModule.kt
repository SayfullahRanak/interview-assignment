package com.setel.assignment.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import androidx.lifecycle.ViewModel
import com.setel.utils.di.ViewModelFactory
import com.setel.utils.di.ViewModelKey
import dagger.Module
import dagger.multibindings.IntoMap

import com.setel.assignment.ui.restaurants.RestaurantDetailViewModel
import com.setel.assignment.ui.restaurants.RestaurantListViewModel


@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantListViewModel::class)
    internal abstract fun restaurantListVM(viewModel: RestaurantListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantDetailViewModel::class)
    internal abstract fun restaurantDetailVM(viewModel: RestaurantDetailViewModel): ViewModel


}