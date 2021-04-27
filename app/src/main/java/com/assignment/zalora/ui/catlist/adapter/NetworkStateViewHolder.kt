package com.assignment.zalora.ui.catlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.assignment.utils.tools.NetworkState
import com.assignment.utils.tools.Status
import com.assignment.zalora.R
import kotlinx.android.synthetic.main.item_network_state.view.*

class NetworkStateViewHolder(val view: View, onRetry : () -> Unit) :RecyclerView.ViewHolder(view) {

    init {
        itemView.btnRetry.setOnClickListener {
            onRetry()
        }
    }

    fun bindTo(networkState : LoadState?){
        if(networkState is LoadState.Loading) itemView.mlLoader.transitionToEnd()
        else itemView.mlLoader.transitionToStart()
    }

    companion object{
        fun create(parent: ViewGroup,onRetry : () -> Unit) : NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            return NetworkStateViewHolder(
                view,
                onRetry
            )
        }
    }
}