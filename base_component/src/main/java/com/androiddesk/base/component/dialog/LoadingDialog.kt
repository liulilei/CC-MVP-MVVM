package com.androiddesk.base.component.dialog

import android.app.Dialog
import android.content.Context
import com.androiddesk.base.component.R

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/4
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.loading_dialog_bg_net) {

    init {
        setContentView(R.layout.dialog_loading)
    }

    override fun dismiss() {
        if (isShowing) {
            super.dismiss()
        }
    }
}

