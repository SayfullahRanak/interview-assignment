package com.assignment.zalora.ui.catDetail

import android.annotation.SuppressLint

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import com.assignment.zalora.R

import com.assignment.utils.base.BaseActivity
import com.assignment.zalora.GlideApp
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_cat_full_screen.*
import kotlinx.android.synthetic.main.item_cat.view.*

@AndroidEntryPoint
class CatViewFullScreen : BaseActivity(false,true) {


    private lateinit var disposable: Disposable

    private  val catsScreenViewModel: CatViewFullScreenViewModel by viewModels()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContentView(R.layout.activity_cat_full_screen)

        catsScreenViewModel.getImageUrl(intent)

        observeViewModel()

    }

    private fun observeViewModel() {
        catsScreenViewModel.imageView.observe(this, Observer { url ->
            loadUrl(url)
        })
    }

    fun loadUrl(url: String){

        GlideApp.with(this)
                .load(url)
                .fitCenter()
                .error(R.drawable.ic_broken_image)
                .into(catImageView)
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::disposable.isInitialized)
            disposable.dispose()
    }


}
