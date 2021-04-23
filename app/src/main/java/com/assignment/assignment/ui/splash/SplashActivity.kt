package com.assignment.assignment.ui.splash

import android.annotation.SuppressLint

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager

import com.assignment.assignment.BuildConfig
import com.assignment.assignment.R

import com.assignment.utils.base.BaseActivity

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(false,false) {


    private lateinit var disposable: Disposable

    private var index = 0

    private var activityIsActive = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContentView(R.layout.activity_splash)

        uatProduction.text = BuildConfig.FLAVOR.substring(0, 1).toUpperCase()
    }


    override fun onResume() {
        super.onResume()
        //check if user login
//        startActivity(
//            Intent(
//                mActivity,
//                RestaurantListActivity::class.java
//            ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//        )
        onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        activityIsActive = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::disposable.isInitialized)
            disposable.dispose()
    }


}
