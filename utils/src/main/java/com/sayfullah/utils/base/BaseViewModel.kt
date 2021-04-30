package com.sayfullah.utils.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val repoLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    open fun onViewCreated() {

    }

    open fun onViewResumed() {

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}