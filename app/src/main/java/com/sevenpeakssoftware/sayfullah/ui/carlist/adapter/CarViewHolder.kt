package com.assignment.zalora.ui.catlist.adapter

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sayfullah.assignment.R
import com.sayfullah.utils.tools.AppUtils
import com.sevenpeakssoftware.sayfullah.data.Content
import com.sevenpeakssoftware.sayfullah.utils.ProjectUtils
import kotlinx.android.synthetic.main.row_car.view.*


class CarViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {


    fun bindTo(content: Content){
//        Glide.with(itemView.context)
//                .asBitmap()
//                .load(content.image)
//                .apply{RequestOptions.placeholderOf(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image)}
//                .into(object  : CustomTarget<Bitmap>(){
//                    override fun onLoadCleared(placeholder: Drawable?) {
//                    }
//
//                    override fun onResourceReady(
//                        resource: Bitmap,
//                        transition: Transition<in Bitmap>?
//                    ) {
//                        itemView.ivCarImage.setImageBitmap(Bitmap.createScaledBitmap(resource,
//                                ProjectUtils.getScreenWidth(itemView.context as Activity),
//                                ProjectUtils.getScreenWidth(itemView.context as Activity),
//                                true))
//                    }
//
//                })
        Glide.with(itemView.context)
                .asBitmap()
                .load(content.image)
                .apply{RequestOptions.placeholderOf(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image)}
                .into(itemView.ivCarImage)

        itemView.titletv.text = content.title
        itemView.ingresstv.text = content.ingress
        itemView.date.text = ProjectUtils.timeFormateChange(content.dateTime)
    }

    companion object{
        fun create(parent: ViewGroup) : CarViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_car, parent, false)
            return CarViewHolder(view)
        }
    }
}