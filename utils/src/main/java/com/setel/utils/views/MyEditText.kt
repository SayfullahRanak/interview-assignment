package com.setel.utils.views

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText
import android.util.AttributeSet

open class MyEditText : AppCompatEditText {

    constructor(context: Context) : super(context){
        setConfig()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        setConfig()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        setConfig()
    }

    fun getValue(): String {
        (return if (text != null) text.toString() else "")
    }


    private fun setConfig() {

        this.maxLines = 1
    }


}