package com.assignment.zalora.ui.catlist.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.assignment.zalora.data.entities.CatModel

import com.assignment.zalora.data.repo.MainRepo
import io.reactivex.disposables.CompositeDisposable

class CatsDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                            private val mainRepo: MainRepo) : DataSource.Factory<Long, CatModel>() {

    val catsDataSourceLiveData = MutableLiveData<CatDataSource>()
    override fun create(): DataSource<Long, CatModel> {
        val catsDataSource =
            CatDataSource(
                mainRepo,
                compositeDisposable
            )
        catsDataSourceLiveData.postValue(catsDataSource)
        return catsDataSource
    }
}