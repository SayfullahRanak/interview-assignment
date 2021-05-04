package com.sevenpeakssoftware.sayfullah.ui.carlist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zalora.ui.catlist.adapter.CarViewHolder
import com.sayfullah.assignment.R
import com.sevenpeakssoftware.sayfullah.data.Content

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class CarListAdapter :
        RecyclerView.Adapter<CarViewHolder>(){

    private var items: List<*>? = ArrayList<Content>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
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
        val item = items?.get(position) as Content
        holder.bindTo(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun <T>updateList(list: List<T>){
        items = list
        notifyDataSetChanged()
    }

//    fun getList() : List<*>{
//        return items!!
//    }
}