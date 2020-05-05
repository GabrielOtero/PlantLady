package br.com.ladyplant.view.components

import android.content.res.Resources

fun Int.toDp(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}