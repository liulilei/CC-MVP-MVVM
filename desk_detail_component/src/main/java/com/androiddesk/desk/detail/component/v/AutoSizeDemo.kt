package com.androiddesk.desk.detail.component.v

import android.view.WindowManager
import androiddesk.com.desk.base.component.base.SimpleActivity
import com.androiddesk.desk.detail.component.R
import me.jessyan.autosize.internal.CustomAdapt

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/21
 */
class AutoSizeDemo : SimpleActivity(), CustomAdapt {

    override fun initStartAnimation() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun initLayout(): Int {
        return R.layout.activity_auto_size_demo
    }

    override fun getSizeInDp(): Float {
        return 640f
    }

    override fun isBaseOnWidth(): Boolean {
        return true
    }
}