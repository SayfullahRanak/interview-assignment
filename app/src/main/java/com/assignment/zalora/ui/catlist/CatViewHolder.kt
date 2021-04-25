package com.assignment.zalora.ui.catlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.cat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cat.view.*


class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(cat : cat?){

        itemView.catPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
        itemView.catPhoto.setPadding(2, 2, 2, 2)

        Glide.with(itemView.context)
            .load(cat?.url)
            .placeholder(R.mipmap.ic_launcher)
            .into(itemView.catPhoto)

    }

    companion object{
        fun create(parent : ViewGroup) : CatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
            return CatViewHolder(view)
        }
    }
}