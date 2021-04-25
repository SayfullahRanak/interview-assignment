package com.assignment.zalora.ui.catlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.assignment.utils.tools.NetworkState
import com.assignment.zalora.data.entities.cat
import com.assignment.zalora.data.repo.MainRepo
import io.reactivex.disposables.CompositeDisposable

class CatsViewModel @ViewModelInject constructor(mainRepo: MainRepo): ViewModel() {

    var catList: LiveData<PagedList<cat>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 30

    private val sourceFactory: CatsDataSourceFactory

    init {
        sourceFactory = CatsDataSourceFactory(compositeDisposable,mainRepo)

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize*2)
            .setEnablePlaceholders(false)
            .build()

        catList = LivePagedListBuilder<Long,cat>(sourceFactory,config).build()
    }

    fun retry() {
        sourceFactory.catsDataSourceLiveData.value!!.retry()
    }

    fun refresh(){
        sourceFactory.catsDataSourceLiveData.value!!.invalidate()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<CatDataSource, NetworkState>(
        sourceFactory.catsDataSourceLiveData, {it.networkState}
    )

    fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<CatDataSource, NetworkState>(
        sourceFactory.catsDataSourceLiveData, { it.initialLoad }
    )

        override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}