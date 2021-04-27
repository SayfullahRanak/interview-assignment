package com.assignment.zalora.ui.catlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.utils.base.BaseActivity
import com.assignment.utils.tools.NetworkState
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.ui.catlist.adapter.CatAdapter
import com.assignment.zalora.utils.AppUtils
import com.assignment.zalora.utils.GridDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_catlist.*


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
            setInitialLoadingState(it)
        })
    }

    private fun initAdapter() {


        val columnSize =
            ((AppUtils.getScreenWidth(this) - (AppUtils.NUM_OF_COLUMNS + 1) * 2) / AppUtils.NUM_OF_COLUMNS).toInt()
        val gridLayoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?
        catAdapter = CatAdapter({ cat ->
            catsViewModel.onClickImage(cat)
        }, columnSize, { catsViewModel.retry() })
        catListView.layoutManager = gridLayoutManager
        catListView.addItemDecoration(GridDecoration())

        val columnWidth =
            ((AppUtils.getScreenWidth(this) - (AppUtils.NUM_OF_COLUMNS + 1) * 2) / AppUtils.NUM_OF_COLUMNS).toInt()

        catListView.adapter = catAdapter

        catsViewModel.catModelList.observe(this, Observer<PagedList<CatModel>> {
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

//        errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
//
//        if (networkState?.message != null) {
//            errorMessageTextView.text = networkState.message
//        }
//
//        //loading and retry
//        retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
//        loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
//
//
//        retryLoadingButton.setOnClickListener { catsViewModel.retry() }

    }




}