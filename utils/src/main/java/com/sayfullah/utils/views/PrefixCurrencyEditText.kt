package com.sayfullah.utils.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.sayfullah.utils.R
import me.abhinay.input.CurrencyEditText
import me.abhinay.input.CurrencySymbols

class PrefixCurrencyEditText : CurrencyEditText {
    private var mOriginalLeftPadding = -1f

    constructor(context: Context) : super(context) {
        config(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.PrefixCurrencyEditText)
        val isMoney = ta.getBoolean(R.styleable.PrefixCurrencyEditText_isMoney, true)
        config(null)
    }

    constructor(context: Context, attrs: AttributeSet,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        config(null)
    }

    fun config(prefixTag: String?) {
        tag = prefixTag ?: "RM: "
        if (tag == "##") tag = "@"
        setCurrency(CurrencySymbols.NONE)
        setDelimiter(false)
        setSpacing(false)
        setDecimals(true)
        setSeparator(".")
        setText("0000")

    }

    override fun onMeasure(widthMeasureSpec: Int,
                           heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        calculatePrefix()
    }

    private fun calculatePrefix() {
        if (mOriginalLeftPadding == -1f) {
            val prefix = tag as String
            val widths = FloatArray(prefix.length)
            paint.getTextWidths(prefix, widths)
            var textWidth = 0f
            for (w in widths) {
                textWidth += w
            }
            mOriginalLeftPadding = compoundPaddingLeft.toFloat()
            setPadding((textWidth + mOriginalLeftPadding).toInt(),
                    paddingRight, paddingTop,
                    paddingBottom)
        }
    }

    protected override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val prefix = tag as String
        canvas.drawText(prefix, mOriginalLeftPadding,
                getLineBounds(0, null).toFloat(), paint)
    }
}