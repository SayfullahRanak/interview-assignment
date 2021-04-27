package com.assignment.zalora.ui.catlist

import android.content.Context
import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.assignment.utils.tools.NetworkState
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.repo.MainRepo
import com.assignment.zalora.ui.catDetail.CatViewFullScreen
import com.assignment.zalora.ui.catlist.datasource.CatDataSource
import com.assignment.zalora.ui.catlist.datasource.CatsDataSourceFactory
import io.reactivex.disposables.CompositeDisposable


class CatsViewModel @ViewModelInject constructor(private val mainRepo: MainRepo, private val context: Context): ViewModel() {

    var catModelList: LiveData<PagedList<CatModel>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory: CatsDataSourceFactory



    init {
        sourceFactory =
            CatsDataSourceFactory(
                compositeDisposable,
                mainRepo
            )

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize*2)
            .setEnablePlaceholders(false)
            .build()

        catModelList = LivePagedListBuilder<Long,CatModel>(sourceFactory,config).build()
    }

    fun retry() {
        sourceFactory.catsDataSourceLiveData.value!!.retry()
    }

    fun onClickImage(catModel: CatModel){

        val intent = Intent(
            context,
            CatViewFullScreen::class.java
        )
        intent.putExtra(context.getString(R.string.detailInformation),catModel)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(
            intent
        )
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