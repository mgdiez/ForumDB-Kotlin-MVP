package com.marcgdiez.jsonplaceholder.extensions

import android.app.AlertDialog
import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcgdiez.jsonplaceholder.R

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Context.internetErrorDialog() {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(R.string.something_went_wrong)
    builder.setCancelable(true)
    builder.create().show()
}