package com.sayfullah.utils.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.codesgood.views.JustifiedTextView
import com.sayfullah.utils.R
import com.sayfullah.utils.tools.Cons

class MyTextView : JustifiedTextView {
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView)

        val textTypeIndex = ta.getInt(R.styleable.MyTextView_textType, 0)

        setFont(context, textTypeIndex)

        ta.recycle()

        includeFontPadding = false
    }


    private fun setFont(context: Context, type: Int) {
        var font = ""
        font = when (type) {
            2 -> Cons.FONT_BOLD
            3 -> Cons.FONT_CREDIT_CARD
            0, 1 -> Cons.FONT_SEMI_BOLD
            else -> Cons.FONT_SEMI_BOLD
        }
        typeface = Typeface.createFromAsset(context.assets, font)
    }
}