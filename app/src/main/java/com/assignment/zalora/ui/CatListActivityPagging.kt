package com.assignment.zalora.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.utils.base.BaseActivity
import com.assignment.utils.tools.NetworkState
import com.assignment.utils.tools.Status
import com.assignment.zalora.R
import com.assignment.zalora.ui.catlist.paging3.CatAdapterPaging
import com.assignment.zalora.ui.catlist.paging3.CatRemoteViewModel
import com.assignment.zalora.ui.catlist.paging3.LoaderStateAdapter
import com.assignment.zalora.utils.AppUtils
import com.assignment.zalora.utils.GridDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_catlist.*
import kotlinx.android.synthetic.main.item_network_state.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CatListActivityPagging : BaseActivity(false,true) {

    private  val catsViewModel: CatRemoteViewModel by viewModels()

    private lateinit var catAdapter: CatAdapterPaging


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_catlist)

        initAdapter()

//        errorMessageTextView.visibility = View.INVISIBLE
//        retryLoadingButton.visibility = View.INVISIBLE
//
//        loadingProgressBar.visibility = View.INVISIBLE
//        initSwipeToRefresh()
    }

//    private fun initSwipeToRefresh() {
//        catsViewModel.getRefreshState().observe(this, Observer {
//            setInitialLoadingState(it)
//        })
//    }

    private fun initAdapter() {


        val columnSize =
            ((AppUtils.getScreenWidth(this) - (AppUtils.NUM_OF_COLUMNS + 1) * 2) / AppUtils.NUM_OF_COLUMNS).toInt()
        val gridLayoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?

        catAdapter =
            CatAdapterPaging({ cat ->
                catsViewModel.onClickImage(cat)
            }, columnSize)

        val loaderStateAdapter = LoaderStateAdapter { catsViewModel.retry() }
        val catAdapterWithFooter = catAdapter.withLoadStateFooter(loaderStateAdapter)

        catListView.layoutManager = gridLayoutManager
        catListView.addItemDecoration(GridDecoration())

        catListView.adapter = catAdapterWithFooter

        fetchCatImages()

//        catsViewModel.catList.observe(this, Observer<PagedList<Cat>> {
//            catAdapter.submitList(it)
//        })
//
//        catsViewModel.getNetworkState().observe(this, Observer<NetworkState> {
//            catAdapter.setNetworkState(it)
//        })
    }

    private fun fetchCatImages() {
        lifecycleScope.launch {
            catsViewModel.fetchCatImages().distinctUntilChanged().collectLatest {
                catAdapter.submitData(it)
            }
        }
    }

    /**
     * Show the current network state for the first load when the user list
     * in the adapter is empty and disable swipe to scroll at the first loading
     *
     * @param networkState the new network state
     */

//    private fun setInitialLoadingState(networkState: NetworkState?) {
//        //error message
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
//
//    }




}