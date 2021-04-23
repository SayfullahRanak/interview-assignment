package com.assignment.utils.tools

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.round

fun String.amountFormat(): String {
    val d = DecimalFormat("0.00")
    return try {
        d.format(this.toDouble())
    } catch (e: Exception) {
        this
    }
}

fun String.log(tag: String? = null) {
    if (tag != null)
        Log.d(tag, this)
    else
        Log.d("debug_", this)
}

infix fun Double.round(decimals: Int): Double {
    var multiplier = 1.00
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun String.toNumberView(): String {
    return this.substring(0, 3) + " " + this.substring(3)
}

fun String.addCharToIndex(char: String, index: Int): String {
    return this.substring(0, index) + char + this.substring(index)
}

fun Float.round(decimals: Int): Float {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toFloat()
}

fun String.toast(ctx: Context?) {
    Toast.makeText(ctx, this, Toast.LENGTH_LONG).show()
}

fun <T : androidx.recyclerview.widget.RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


// 8 sen and RM 10 example should return 00001000
fun String.senPrice(  long: Int = 8): String {
    return ("0".repeat(long - this.length)).plus(this)
}

fun Int.length() = when(this) {
    0 -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun Double.length() = when(this) {
    0.toDouble() -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun Long.length() = when(this) {
    0.toLong() -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

