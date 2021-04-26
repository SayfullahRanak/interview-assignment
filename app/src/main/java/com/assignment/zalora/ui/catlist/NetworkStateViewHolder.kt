package com.assignment.zalora.ui.catlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.assignment.utils.tools.NetworkState
import com.assignment.utils.tools.Status
import com.assignment.zalora.R
import kotlinx.android.synthetic.main.item_network_state.view.*

class NetworkStateViewHolder(val view: View, onRetry : () -> Unit) :RecyclerView.ViewHolder(view) {

    init {
        itemView.retryLoadingButton.setOnClickListener {
            onRetry()
        }
    }

    fun bindTo(networkState : NetworkState?){
        itemView.errorMessageTextView.visibility = if(networkState?.message !=null) View.VISIBLE else View.GONE

        if(networkState?.message !=null){
            itemView.errorMessageTextView.text = networkState.message
        }

        itemView.retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        itemView.loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
    }

    companion object{
        fun create(parent: ViewGroup,onRetry : () -> Unit) : NetworkStateViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            return NetworkStateViewHolder(view,onRetry)
        }
    }
}