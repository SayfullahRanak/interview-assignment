package com.assignment.zalora.ui.catlist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sayfullah.assignment.R
import com.sevenpeakssoftware.sayfullah.data.Content

class CarListAdapter() :
        RecyclerView.Adapter<CarViewHolder>(){

    private var items: List<Content>? = ArrayList<Content>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder{
        return when(viewType){
            R.layout.row_car -> CarViewHolder.create(
                parent
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_car
    }


    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bindTo(item!!)
    }

    override fun getItemCount(): Int {
        val count = items?.size ?: 0
        return count
    }

    fun updateList(list: List<Content>){
        items = list
        notifyDataSetChanged()
    }

    fun getList() : List<Content>{
        return items!!
    }
}