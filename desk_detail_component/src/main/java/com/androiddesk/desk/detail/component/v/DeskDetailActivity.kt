package com.androiddesk.desk.detail.component.v

import android.content.Intent
import androiddesk.com.desk.base.component.base.SimpleActivity
import com.androiddesk.desk.detail.component.R
import kotlinx.android.synthetic.main.desk_detail.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/16
 */
class DeskDetailActivity : SimpleActivity() {
    override fun initLayout(): Int {
        return R.layout.desk_detail
    }

    override fun initViewAndData() {
        tv.setOnClickListener {
            startActivity(Intent(this, AutoSizeDemo::class.java))
        }
    }
}