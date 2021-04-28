package com.assignment.zalora.ui.catlist.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.utils.base.BaseActivity
import com.assignment.zalora.R
import com.assignment.zalora.ui.catlist.viewmodel.CatRemoteViewModel
import com.assignment.zalora.ui.catlist.adapter.CatListAdapter
import com.assignment.zalora.ui.catlist.adapter.LoaderStateAdapter
import com.assignment.zalora.utils.AppUtils
import com.assignment.zalora.utils.GridDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_catlist.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@AndroidEntryPoint
@ExperimentalPagingApi
class CatListActivity : BaseActivity(false,true) {

    private  val catsViewModel: CatRemoteViewModel by viewModels()

    private lateinit var catListAdapter: CatListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_catlist)

        initAdapter()

    }


    private fun initAdapter() {

        val gridLayoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?

        catListAdapter =
            CatListAdapter({ cat ->
                catsViewModel.onClickImage(cat)
            }, getColumnSize())

        val loaderStateAdapter =
            LoaderStateAdapter { catListAdapter.retry() }
        val catAdapterWithFooter = catListAdapter.withLoadStateFooter(loaderStateAdapter)

        catListView.layoutManager = gridLayoutManager
        catListView.addItemDecoration(GridDecoration())

        catListView.adapter = catAdapterWithFooter

        fetchCatImages()

    }



    private fun fetchCatImages() {
        lifecycleScope.launch {
            catsViewModel.fetchCatImages().distinctUntilChanged().collectLatest {
                catListAdapter.submitData(it)
            }
        }
    }

    private fun getColumnSize() : Int{
        return ((AppUtils.getScreenWidth(this) - (AppUtils.NUM_OF_COLUMNS + 1) * 2) / AppUtils.NUM_OF_COLUMNS).toInt()
    }

}