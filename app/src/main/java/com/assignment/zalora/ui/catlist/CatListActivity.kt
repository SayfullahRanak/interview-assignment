package com.assignment.zalora.ui.catlist

import android.R.attr.columnWidth
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.GridView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.utils.base.BaseActivity
import com.assignment.utils.tools.Utils
import com.assignment.utils.tools.NetworkState
import com.assignment.utils.tools.Status
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.Cat
import com.assignment.zalora.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_catlist.*
import kotlinx.android.synthetic.main.item_network_state.*


@AndroidEntryPoint
class CatListActivity : BaseActivity(false,true) {

    private  val catsViewModel: CatsViewModel by viewModels()

    private lateinit var catAdapter: CatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_catlist)

        initAdapter()
        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
        catsViewModel.getRefreshState().observe(this, Observer {
            if(catAdapter.currentList !=null){
                if(catAdapter.currentList!!.size>0){
                    swipeRefreshLayout.isRefreshing = it?.status == NetworkState.LOADING.status
                }else{
                    setInitialLoadingState(it)
                }
            }else{
                setInitialLoadingState(it)
            }
        })
        swipeRefreshLayout.setOnRefreshListener({ catsViewModel.refresh() })
    }

    private fun initAdapter() {

        val gridLayoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?
        catAdapter = CatAdapter({url -> catsViewModel.onClickImage(url)})


        catListView.layoutManager = gridLayoutManager

        catListView.adapter = catAdapter

        catsViewModel.catList.observe(this, Observer<PagedList<Cat>> {
            catAdapter.submitList(it)
        })

        catsViewModel.getNetworkState().observe(this, Observer<NetworkState> {
            catAdapter.setNetworkState(it)
        })
    }

    /**
     * Show the current network state for the first load when the user list
     * in the adapter is empty and disable swipe to scroll at the first loading
     *
     * @param networkState the new network state
     */

    private fun setInitialLoadingState(networkState: NetworkState?) {
        //error message
        errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE

        if (networkState?.message != null) {
            errorMessageTextView.text = networkState.message
        }

        //loading and retry
        retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE

        swipeRefreshLayout.isEnabled = networkState?.status == Status.SUCCESS

        retryLoadingButton.setOnClickListener { catsViewModel.retry() }

    }


//    private fun InitilizeGridLayout() {
//        val r = resources
//        val padding = TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            AppUtils.GRID_PADDING, r.displayMetrics
//        )
//        val columnWidth =
//            ((AppUtils.getScreenWidth(this) - (AppUtils.NUM_OF_COLUMNS + 1) * padding) / AppUtils.NUM_OF_COLUMNS).toInt()
//
//        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS)
//        gridView.setColumnWidth(columnWidth)
//        gridView.setStretchMode(GridView.NO_STRETCH)
//        gridView.setPadding(
//            padding.toInt(), padding.toInt(), padding.toInt(),
//            padding.toInt()
//        )
//        gridView.setHorizontalSpacing(padding.toInt())
//        gridView.setVerticalSpacing(padding.toInt())
//    }


}