package com.tae.remembersearchapplication.ext

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.RememberApp

@SuppressLint("InflateParams")
fun Context.progressDialog(): Dialog {
    val dialog = Dialog(this)
    val inflate = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null)
    dialog.setContentView(inflate)
    dialog.setCancelable(false)
    dialog.window?.setBackgroundDrawable(
        ColorDrawable(Color.TRANSPARENT)
    )
    return dialog
}

fun Context.showDialog(msg: String) {
    val builder = AlertDialog.Builder(this)
    val message = msg.ifEmpty { "" }
    builder.setTitle("")
        .setMessage(message)
        .setPositiveButton(RememberApp.INSTANCE.getString(android.R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false)

    val dialog = builder.create()
    if (! dialog.isShowing) builder.show()
}