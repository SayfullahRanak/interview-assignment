package com.assignment.zalora.ui.catlist

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.utils.tools.NetworkState
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.cat

class CatAdapter(private val retryCallback: () -> Unit) : PagedListAdapter<cat,RecyclerView.ViewHolder>(UserDiffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.item_cat -> CatViewHolder.create(parent)
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent,retryCallback)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            R.layout.item_cat -> (holder as CatViewHolder).bindTo(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).bindTo(networkState)
        }
    }




    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if(hasExtraRow() && position == itemCount -1){
            R.layout.item_network_state
        }else {
            R.layout.item_cat
        }
    }

    private fun hasExtraRow() : Boolean{
        return networkState !=null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if (currentList != null) {
            if (currentList!!.size != 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<cat>(){
            override fun areItemsTheSame(oldItem: cat, newItem: cat): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: cat, newItem: cat): Boolean {
                return oldItem == newItem
            }

        }
    }
}