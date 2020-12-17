package com.setel.assignment.ui.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setel.assignment.R
import com.setel.assignment.utils.AppUtils.Companion.checkTime
import com.setel.utils.tools.listen
import com.setel.utils.views.MyTextView
import com.setel.assignment.data.entities.Restaurant
import kotlinx.android.synthetic.main.restaurant_item.view.*


class RestaurantAdapter(val items: List<Restaurant>, val currentTime : Long) :
    androidx.recyclerview.widget.RecyclerView.Adapter<RestaurantAdapter.Holder>() {
    lateinit var clickHandler: OnClick


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        )
            .listen { pos, _ ->
                clickHandler.onClick(items[pos])
            }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.restaurantName.text = items[position].name
        holder.operatingStatus.text = if (checkTime(items[position].operatingHours, currentTime)) "Open" else "Close"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val restaurantName: MyTextView = view.name
        val operatingStatus: MyTextView = view.status
    }

    interface OnClick {
        fun onClick(restaurant: Restaurant)
    }

}