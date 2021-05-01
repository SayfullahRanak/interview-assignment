package com.assignment.zalora.ui.catlist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sayfullah.assignment.R
import com.sevenpeakssoftware.sayfullah.data.Content

class CarListAdapterPaging() : PagingDataAdapter<Content, RecyclerView.ViewHolder>(
    UserDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.row_car -> CarViewHolder.create(
                parent
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CarViewHolder).bindTo(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_car
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Content>(){
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }

        }
    }
}