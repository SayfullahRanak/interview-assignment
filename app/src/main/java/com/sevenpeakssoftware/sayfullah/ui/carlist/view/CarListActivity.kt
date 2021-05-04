package com.sevenpeakssoftware.sayfullah.ui.carlist.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakewharton.rxbinding2.widget.indeterminate
import com.sevenpeakssoftware.sayfullah.ui.carlist.adapter.CarListAdapter
import com.sayfullah.assignment.R
import com.sayfullah.utils.base.BaseActivity
import com.sayfullah.utils.tools.AppUtils
import com.sevenpeakssoftware.sayfullah.data.ErrorIn
import com.sevenpeakssoftware.sayfullah.data.Loading
import com.sevenpeakssoftware.sayfullah.data.Success
import com.sevenpeakssoftware.sayfullah.ui.carlist.viewmodel.CarRemoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_carlist.*
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@AndroidEntryPoint
class CarListActivity : BaseActivity(false,true), SwipeRefreshLayout.OnRefreshListener {

    private  val carListViewModel: CarRemoteViewModel by viewModels()

    private lateinit var carListAdapter: CarListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carlist)

        swipelayout.setOnRefreshListener(this)

        initAdapter()

        observeViewModel()

    }




    private fun initAdapter() {

        carListAdapter =
            CarListAdapter()

        carListrv.layoutManager = LinearLayoutManager(this) 

        carListrv.adapter = carListAdapter

        fetchCarList()

    }



    private fun fetchCarList() {
        lifecycleScope.launch {
            carListViewModel.fetchCarList()
        }
    }

    private fun observeViewModel(){
        carListViewModel.carListlv.observe(this, Observer {
            when(it){
                is Success ->{
                    val carListResponse = it.data as List<*>
                    carListAdapter.updateList(carListResponse)
                    loading(false)
                }
                is ErrorIn ->{
                    AppUtils.showAlert(this,"Error in network",it.message,true)
                }
                is Loading ->{
                    loading(true)
                }
            }
        })

        carListViewModel.networkStatus.observe(this, Observer {
            noInternet.visibility = if(it) View.GONE else View.VISIBLE
        })
    }

    override fun onRefresh() {
        swipelayout.isRefreshing = false
        fetchCarList()
    }

    override fun onBackPressed() {
        val layoutManager = carListrv
            .layoutManager as LinearLayoutManager
        if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            super.onBackPressed()
        } else {
            carListrv.smoothScrollToPosition(0)
        }
    }

    private fun loading(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.INVISIBLE
    }
    
    

}