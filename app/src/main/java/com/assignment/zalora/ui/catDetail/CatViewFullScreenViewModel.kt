package com.assignment.zalora.ui.catDetail

import android.content.Context
import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.assignment.utils.base.BaseViewModel
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.CatModel


class CatViewFullScreenViewModel @ViewModelInject constructor(private val context: Context) : BaseViewModel() {

    val imageView = MutableLiveData<String>()


    fun getImageUrl(intent: Intent){

        if (intent.hasExtra(context.getString(R.string.detailInformation))) {
            val detailInformation = intent.getSerializableExtra(context.getString(R.string.detailInformation)) as? CatModel

            imageView.postValue(detailInformation?.url)

        }else{
            imageView.postValue("")
        }


    }
}