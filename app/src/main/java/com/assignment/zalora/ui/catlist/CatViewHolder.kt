package com.assignment.zalora.ui.catlist

import android.graphics.Bitmap
import android.graphics.BitmapFactory.decodeFile
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.Cat
import com.assignment.zalora.utils.AppUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.item_cat.view.*


class CatViewHolder(view: View, private val onClickCallBack : (cat: Cat) ->Unit, private val imageSize : Int) : RecyclerView.ViewHolder(view) {



    fun bindTo(cat : Cat?){
        Glide.with(itemView.context)
                .asBitmap()
                .load(cat?.url)
                .into(object  : CustomTarget<Bitmap>(){
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        itemView.catPhoto.setOnClickListener {
                            onClickCallBack(cat!!)
                        }
                        itemView.catPhoto.setImageBitmap(Bitmap.createScaledBitmap(resource, imageSize, imageSize, true))

                    }

                })



    }

    companion object{
        fun create(parent: ViewGroup, onClickCallBack: (cat: Cat) -> Unit, imageSize : Int) : CatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
            return CatViewHolder(view,onClickCallBack,imageSize)
        }
    }
}