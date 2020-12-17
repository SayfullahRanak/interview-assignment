package com.setel.assignment.ui.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setel.assignment.R
import com.setel.utils.views.MyTextView
import com.setel.assignment.data.entities.Restaurant
import com.setel.assignment.data.entities.RestaurantTiming
import kotlinx.android.synthetic.main.row_restaurant_timing.view.*


class OperatingHourAdapter(val items: List<RestaurantTiming>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<OperatingHourAdapter.Holder>() {
    lateinit var clickHandler: OnClick


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_restaurant_timing, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.weekdays.text = items[position].weekdays.trim()
        holder.operatingTime.text = items[position].timing.trim()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val weekdays: MyTextView = view.weekday
        val operatingTime: MyTextView = view.operatingTime
    }

    interface OnClick {
        fun onClick(restaurant: Restaurant)
    }
}