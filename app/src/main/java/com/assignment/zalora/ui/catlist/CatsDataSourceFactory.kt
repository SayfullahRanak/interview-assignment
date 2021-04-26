package com.assignment.zalora.ui.catlist

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.assignment.zalora.data.entities.Cat

import com.assignment.zalora.data.repo.MainRepo
import io.reactivex.disposables.CompositeDisposable

class CatsDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                            private val mainRepo: MainRepo) : DataSource.Factory<Long, Cat>() {

    val catsDataSourceLiveData = MutableLiveData<CatDataSource>()
    override fun create(): DataSource<Long, Cat> {
        val catsDataSource = CatDataSource(mainRepo, compositeDisposable)
        catsDataSourceLiveData.postValue(catsDataSource)
        return catsDataSource
    }
}