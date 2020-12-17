package com.setel.assignment.ui.splash

import android.annotation.SuppressLint

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager

import com.setel.assignment.BuildConfig
import com.setel.assignment.R

import com.setel.utils.base.BaseActivity
import com.setel.assignment.ui.restaurants.RestaurantListActivity

import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*

import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity(isFullScreen = true) {


    private lateinit var disposable: Disposable

    private var index = 0

    private var activityIsActive = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)

        uatProduction.text = BuildConfig.FLAVOR.substring(0, 1).toUpperCase()
    }


    override fun onResume() {
        super.onResume()
        //check if user login
        startActivity(
            Intent(
                mActivity,
                RestaurantListActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        )
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
