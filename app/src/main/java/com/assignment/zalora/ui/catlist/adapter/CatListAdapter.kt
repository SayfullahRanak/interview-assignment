package com.assignment.zalora.ui.catlist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.CatModel

class CatListAdapter(private val onItemClick : (catModel: CatModel) -> Unit, private val columnSize: Int) : PagingDataAdapter<CatModel, RecyclerView.ViewHolder>(
    UserDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.item_cat -> CatViewHolder.create(
                parent,
                onItemClick,
                columnSize
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CatViewHolder).bindTo(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_cat
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<CatModel>(){
            override fun areItemsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}