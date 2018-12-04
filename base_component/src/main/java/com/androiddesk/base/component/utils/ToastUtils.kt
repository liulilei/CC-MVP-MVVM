package com.androiddesk.base.component.utils

import android.content.Context
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.androiddesk.base.component.R

/**
 * ToastUtils
 */
object ToastUtils {

    fun show(context: Context, resId: Int) {
        showToast(context, context.resources.getText(resId), Toast.LENGTH_SHORT)
    }

    fun show(context: Context, resId: Int, duration: Int) {
        showToast(context, context.resources.getText(resId), duration)
    }

    fun show(context: Context, resId: Int, vararg args: Any) {
        showToast(context, String.format(context.resources.getString(resId), *args), Toast.LENGTH_SHORT)
    }

    fun show(context: Context, format: String, vararg args: Any) {
        showToast(context, String.format(format, *args), Toast.LENGTH_SHORT)
    }

    fun show(context: Context, resId: Int, duration: Int, vararg args: Any) {
        showToast(context, String.format(context.resources.getString(resId), *args), duration)
    }

    fun show(context: Context, format: String, duration: Int, vararg args: Any) {
        showToast(context, String.format(format, *args), duration)
    }

    private fun showToast(context: Context, text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        if (AppUtils.isInMainThread()) {
            makeToast(context, text, duration).show()
        } else {
            Looper.prepare()
            makeToast(context, text, duration).show()
            Looper.loop()
        }
    }

    private fun makeToast(context: Context, msg: CharSequence, duration: Int): Toast {
        val toast = Toast(context)
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflate.inflate(R.layout.toast_layout, null)
        val msgTv: TextView = view.findViewById(R.id.toast_msg)
        msgTv.text = msg
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.view = view
        toast.duration = duration
        return toast
    }
}
