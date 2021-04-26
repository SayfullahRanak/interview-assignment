package com.assignment.zalora.ui.catlist

import android.graphics.Bitmap
import android.graphics.BitmapFactory.decodeFile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zalora.R
import com.assignment.zalora.data.entities.Cat
import com.assignment.zalora.utils.AppUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cat.view.*


class CatViewHolder(view: View, private val onClickCallBack : (url : String) ->Unit) : RecyclerView.ViewHolder(view) {



    fun bindTo(Cat : Cat?){


        // get screen dimensions
        // get screen dimensions
//        val image: Bitmap = AppUtils.decodeFile(
//            _filePaths.get(position), imageWidth,
//            imageWidth
//        )
//
//        itemView.catPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP)
//        itemView.catPhoto.setLayoutParams(
//            ViewGroup.LayoutParams(
//                imageWidth,
//                imageWidth
//            )
//        )
//        imageView.setImageBitmap(image)

        Glide.with(itemView.context)
                .load(Cat?.url)
                .fitCenter()
                .into(itemView.catPhoto)


        itemView.catPhoto.setOnClickListener {
            onClickCallBack(Cat?.url!!)
        }

    }

    companion object{
        fun create(parent: ViewGroup, onClickCallBack: (url: String) -> Unit) : CatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
            return CatViewHolder(view,onClickCallBack)
        }
    }
}