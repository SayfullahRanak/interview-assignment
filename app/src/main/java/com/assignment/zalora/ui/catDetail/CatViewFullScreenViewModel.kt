package com.assignment.zalora.ui.catDetail

import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.assignment.utils.base.BaseViewModel


class CatViewFullScreenViewModel @ViewModelInject constructor() : BaseViewModel() {

    val imageView = MutableLiveData<String>()


    fun getImageUrl(intent: Intent){

        val url = intent.getStringExtra("url")

        imageView.postValue(url!!)

    }
}