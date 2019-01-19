package com.androiddesk.desk.detail.component.v

import android.view.View
import com.androiddesk.desk.detail.component.R
import com.androiddesk.desk.detail.component.mvvm.base.BaseActivity
import com.androiddesk.desk.detail.component.vm.DeskDetailViewMolder
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCUtil
import kotlinx.android.synthetic.main.base_title.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/16
 */
class DeskDetailActivity : BaseActivity<DeskDetailViewMolder>() {

    companion object {
        fun start(cc: CC) {
            CCUtil.navigateTo(cc, DeskDetailActivity::class.java)
        }
    }

    override fun initLayout(): Int {
        return R.layout.desk_detail
    }

    override fun initViewAndData() {
        titleTv.text = "详情"
        leftIv.visibility = View.VISIBLE
        onclick()
    }

    private fun onclick() {
        leftIv.setOnClickListener {
            finish()
        }
    }
}