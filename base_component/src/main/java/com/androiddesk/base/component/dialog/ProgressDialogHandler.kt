package com.androiddesk.base.component.dialog

import android.content.Context
import android.os.Handler
import android.os.Message

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/4
 */
class ProgressDialogHandler(var context: Context, var mProgressCancelListener: ProgressCancelListener, var cancelable: Boolean) : Handler() {

    companion object {
        const val SHOW_PROGRESS_DIALOG = 1
        const val DISMISS_PROGRESS_DIALOG = 2
    }

    private var pd: LoadingDialog? = null

    private fun initProgressDialog() {
        if (pd == null) {
            pd = LoadingDialog(context)

            pd?.setCancelable(cancelable)

            if (cancelable) {
                pd?.setOnCancelListener({
                    if (mProgressCancelListener != null) {
                        mProgressCancelListener.onCancelProgress()
                    }
                })
            }

            if (!pd?.isShowing!!) {
                pd?.show()
            }
        }
    }

    private fun dismissProgressDialog() {
        if (pd != null && pd?.isShowing!!) {
            pd?.dismiss()
            pd = null
        }
    }

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            SHOW_PROGRESS_DIALOG -> initProgressDialog()
            DISMISS_PROGRESS_DIALOG -> dismissProgressDialog()
        }
    }
}