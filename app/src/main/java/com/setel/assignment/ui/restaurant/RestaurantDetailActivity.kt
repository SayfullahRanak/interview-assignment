package com.setel.assignment.ui.restaurants

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.setel.assignment.R
import com.setel.utils.base.BaseActivity
import com.setel.utils.tools.AppUtils
import com.setel.assignment.utils.AppUtils.Companion.timingStringToList
import com.setel.assignment.data.entities.Restaurant
import com.setel.assignment.data.entities.RestaurantTiming

import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import kotlinx.android.synthetic.main.activity_restaurant_list.restaurantListView
import javax.inject.Inject
import kotlin.math.log

class RestaurantDetailActivity : BaseActivity(), OperatingHourAdapter.OnClick {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RestaurantDetailViewModel

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        AndroidInjection.inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RestaurantDetailViewModel::class.java)
        observeVM()

        back.setOnClickListener { onBackPressed() }

        val restaurantDetail = Gson().fromJson(intent.getStringExtra("restaurantcontent"),Restaurant::class.java)
        val restaurantDetailList = timingStringToList(restaurantDetail.operatingHours)
        restaurantTitle.text = restaurantDetail.name
        observeRestaurantTiming(restaurantDetailList)
    }


    private fun observeRestaurantTiming(restaurantDetailList: List<RestaurantTiming>) {
        loading(true)
        val adapter = OperatingHourAdapter(restaurantDetailList)
        restaurantTimingView.adapter = adapter.also { it.clickHandler=this }
        loading(false)
//        viewModel.getRestaurants()
    }

    private fun observeVM() {
        viewModel.restaurantList.observe(this, Observer {

        })
        viewModel.error.observe(this, Observer {
            loading(false)
            AppUtils.showAlert(mActivity, null, (it ?: getString(R.string.error_in_loading)), false)

        })
    }

    override fun onStop() {
        super.onStop()
        disposables.dispose()
    }

    override fun onClick(restaurant: Restaurant) {
//        val intent = Intent()
//        intent.putExtra("phone", contact.phoneNumbers[0].normalizedNumber)
//        setResult(Activity.RESULT_OK, intent)
//        finish()

    }

}