package com.assignment.zalora.ui.catlist.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.CatModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.item_cat.view.*


class CatViewHolder(view: View, private val onClickCallBack : (catModel: CatModel) ->Unit, private val imageSize : Int) : RecyclerView.ViewHolder(view) {


    fun bindTo(catModel : CatModel?){
        Glide.with(itemView.context)
                .asBitmap()
                .load(catModel?.url)
                .into(object  : CustomTarget<Bitmap>(){
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        itemView.catPhoto.setOnClickListener {
                            onClickCallBack(catModel!!)
                        }
                        itemView.catPhoto.setImageBitmap(Bitmap.createScaledBitmap(resource, imageSize, imageSize, true))

                    }

                })



    }

    companion object{
        fun create(parent: ViewGroup, onClickCallBack: (catModel: CatModel) -> Unit, imageSize : Int) : CatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
            return CatViewHolder(
                view,
                onClickCallBack,
                imageSize
            )
        }
    }
}