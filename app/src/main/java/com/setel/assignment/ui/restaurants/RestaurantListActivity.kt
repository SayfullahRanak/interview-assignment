package com.setel.assignment.ui.restaurants

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.setel.assignment.R
import com.setel.utils.base.BaseActivity
import com.setel.utils.tools.AppUtils

import com.setel.assignment.data.entities.Restaurant

import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import javax.inject.Inject

class RestaurantListActivity : BaseActivity(), RestaurantAdapter.OnClick {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RestaurantListViewModel

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        AndroidInjection.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RestaurantListViewModel::class.java)
        observeVM()

        observeContactsList()
    }


    private fun observeContactsList() {
        loading(true)
        viewModel.getRestaurants()
    }

    private fun observeVM() {
        viewModel.restaurantList.observe(this, Observer {
            val adapter = RestaurantAdapter(it.restaurants,it.timestamp)
            restaurantListView.adapter = adapter.also { it.clickHandler=this }
            loading(false)
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

        val intent = Intent(this,RestaurantDetailActivity::class.java)
        intent.putExtra("restaurantcontent", Gson().toJson(restaurant).toString())
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}