package com.assignment.zalora.ui.catlist

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.assignment.utils.tools.NetworkState
import com.assignment.zalora.data.entities.Cat
import com.assignment.zalora.data.repo.MainRepo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class CatDataSource (private val mainRepo: MainRepo, private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Long, Cat>() {

    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    private var sourceIndex : Long = 0

    /**
     * Keep Completable reference for the retry event
     */

    private var retryCompletable : Completable? = null

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Cat>) {

        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded.
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        //get the initial users from the api

        compositeDisposable.add(mainRepo.getUsers(params.requestedLoadSize.toLong(),sourceIndex).subscribe({ users ->

            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)

            callback.onResult(users, null,++sourceIndex)
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadInitial(params, callback) })
            val error = NetworkState.error(throwable.message)
            // publish the error
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Cat>) {

        // set network value to loading.
        networkState.postValue(NetworkState.LOADING)



        //get the users from the api after id
        compositeDisposable.add(mainRepo.getUsers(params.requestedLoadSize.toLong(),params.key).subscribe({ users ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)

            callback.onResult(users, ++sourceIndex)
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadAfter(params, callback) })
            // publish the error
            networkState.postValue(NetworkState.error(throwable.message))
        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Cat>) {
        // ignored, since we only ever append to our initial load
    }



    fun retry(){
        if(retryCompletable!=null){
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, { throwable -> "e(throwable.message)" }))
        }
    }

    private fun setRetry(action : Action?){
        if(action ==  null){
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }



}