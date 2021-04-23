package com.assignment.utils.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class CustomButton : AppCompatButton {
    constructor(context: Context) : super(context) {
        setBackground(context, false)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    private fun setBackground(context: Context, isEmpty: Boolean) {


    }
}