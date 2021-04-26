package com.assignment.zalora.ui.catlist

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.assignment.utils.tools.NetworkState
import com.assignment.zalora.data.entities.Cat
import com.assignment.zalora.data.repo.MainRepo
import com.assignment.zalora.ui.catDetail.CatViewFullScreen
import io.reactivex.disposables.CompositeDisposable

class CatsViewModel @ViewModelInject constructor(private val mainRepo: MainRepo, private val context: Context): ViewModel() {

    var catList: LiveData<PagedList<Cat>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory: CatsDataSourceFactory

    init {
        sourceFactory = CatsDataSourceFactory(compositeDisposable,mainRepo)

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize*2)
            .setEnablePlaceholders(false)
            .build()

        catList = LivePagedListBuilder<Long,Cat>(sourceFactory,config).build()
    }

    fun retry() {
        sourceFactory.catsDataSourceLiveData.value!!.retry()
    }

    fun onClickImage(url : String){

        val intent = Intent(
            context,
            CatViewFullScreen::class.java
        )
        intent.putExtra("url",url)
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

//    fun getItems(ctx: Context, columns: Int): LiveData<List<Cat>> {
//        return Transformations.map(GalleryRepository().getResponse(ctx)) { r ->
//            val list = ArrayList<Cat>()
//            val row = ArrayList<Cat>()
//            var rowRatios = 0f
//            r!!.value!!.forEach { it: Result ->
//                val imageRatio = it.width!! / it.height!!.toFloat()
//                val item = Cat(
//                    it.imageId as ItemId,
//                    it.name!!,
//                    Color.parseColor("#" + it.accentColor),
//                    it.thumbnailUrl!!,
//                    imageRatio
//                )
//                list.add(item)
//                rowRatios += item.imageRatio
//                if (rowRatios > 2f) {
//                    var used = 0
//                    row.forEach { it2: Item ->
//                        it2.columns = ((columns * it2.imageRatio) / rowRatios).toInt()
//                        used += it2.columns
//                    }
//                    item.columns = columns - used
//                    row.clear()
//                    rowRatios = 0f
//                } else {
//                    row.add(item)
//                }
//            }
//            list
//        }
//    }
}