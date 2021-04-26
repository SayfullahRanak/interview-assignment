package com.assignment.zalora.utils


import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.assignment.zalora.R

class GridDecoration : RecyclerView.ItemDecoration() {

        val margin1Px =
            MyApplication.appInstance.resources.getDimension(R.dimen.padding1px).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.right = margin1Px
        outRect.left = margin1Px
        outRect.top = margin1Px
        outRect.bottom = margin1Px

    }
}