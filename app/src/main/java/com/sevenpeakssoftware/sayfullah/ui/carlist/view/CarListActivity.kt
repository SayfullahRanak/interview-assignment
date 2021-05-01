package com.sevenpeakssoftware.sayfullah.ui.carlist.view

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.zalora.ui.catlist.adapter.CarListAdapter
import com.sayfullah.assignment.R
import com.sevenpeakssoftware.sayfullah.ui.carlist.BaseActivity
import com.sayfullah.utils.tools.AppUtils
import com.sevenpeakssoftware.sayfullah.data.Content
import com.sevenpeakssoftware.sayfullah.data.ErrorIn
import com.sevenpeakssoftware.sayfullah.data.Loading
import com.sevenpeakssoftware.sayfullah.data.Success
import com.sevenpeakssoftware.sayfullah.ui.carlist.viewmodel.CarRemoteViewModel
import com.sevenpeakssoftware.sayfullah.utils.NetworkChangeReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_carlist.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CarListActivity : BaseActivity(false,true), SwipeRefreshLayout.OnRefreshListener {

    private  val carListViewModel: CarRemoteViewModel by viewModels()

    private lateinit var carListAdapter: CarListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carlist)

        swipelayout.setOnRefreshListener(this)

        initAdapter()

        ObserveViewModel()

    }




    private fun initAdapter() {

        carListAdapter =
            CarListAdapter()

        carListrv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        carListrv.adapter = carListAdapter

        fetchCarList()

    }



    private fun fetchCarList() {
        lifecycleScope.launch {
            carListViewModel.fetchCarList()
        }
    }

    private fun ObserveViewModel(){
        carListViewModel.carListlv.observe(this, Observer {
            when(it){
                is Success ->{
                    val carListResponse = it.data as List<Content>
                    carListAdapter.updateList(carListResponse)
                    loading(false)
                }
                is ErrorIn ->{
                    AppUtils.showAlert(this,"Error in network",it.message,true)
                }
                is Loading ->{
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
            .getLayoutManager() as LinearLayoutManager
        if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            super.onBackPressed()
        } else {
            carListrv.smoothScrollToPosition(0)
        }
    }


    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //    private fun getColumnSize() : Int{
//        return ((AppUtils.getScreenWidth(this) - (AppUtils.NUM_OF_COLUMNS + 1) * 2) / AppUtils.NUM_OF_COLUMNS).toInt()
//    }

}