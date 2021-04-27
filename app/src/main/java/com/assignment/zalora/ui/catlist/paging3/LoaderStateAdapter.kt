package com.assignment.zalora.ui.catlist.paging3

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.assignment.zalora.R
import com.assignment.zalora.ui.catlist.adapter.CatViewHolder
import com.assignment.zalora.ui.catlist.adapter.NetworkStateViewHolder

class LoaderStateAdapter(private val onRetry : () -> Unit) : LoadStateAdapter<NetworkStateViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateViewHolder {

       return NetworkStateViewHolder.create(
                parent,
                onRetry
       )
    }
}