package com.assignment.zalora.ui.catlist.paging3

import android.content.Context
import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.assignment.utils.base.BaseViewModel
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.repo.MainRepo
import com.assignment.zalora.ui.catDetail.CatViewFullScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatRemoteViewModel @ViewModelInject constructor(private val context: Context, private val mainRepo: MainRepo) : BaseViewModel() {

    fun fetchCatImages() : Flow<PagingData<CatModel>>{
        return CatImagesRepository.getInstance(mainRepo).letCatsImagesFlow().map { it }.cachedIn(viewModelScope)
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

    fun retry() {
//        sourceFactory.catsDataSourceLiveData.value!!.retry()
    }

}