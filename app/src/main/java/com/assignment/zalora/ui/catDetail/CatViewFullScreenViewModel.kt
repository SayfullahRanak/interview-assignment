package com.assignment.zalora.ui.catDetail

import android.content.Intent
import android.content.Intent.getIntent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.assignment.utils.base.BaseViewModel
import com.assignment.zalora.data.entities.Cat


class CatViewFullScreenViewModel @ViewModelInject constructor() : BaseViewModel() {

    val imageView = MutableLiveData<String>()


    fun getImageUrl(intent: Intent){

        if (intent.hasExtra("DETAILINFORMATION")) {
            val detailInformation = intent.getSerializableExtra("DETAILINFORMATION") as? Cat

            imageView.postValue(detailInformation?.url)

        }


    }
}