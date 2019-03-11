package com.androiddesk.desk.detail.component.v

import android.view.View
import androiddesk.com.desk.base.component.processor.ComponentDetail
import com.androiddesk.base.component.utils.ParseDataUtils
import com.androiddesk.base.component.utils.logger.LogUtils
import com.androiddesk.desk.detail.component.R
import com.androiddesk.desk.detail.component.m.VerticalInfo
import com.androiddesk.desk.detail.component.mvvm.base.BaseActivity
import com.androiddesk.desk.detail.component.vm.DeskDetailViewMolder
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCUtil
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import kotlinx.android.synthetic.main.base_title.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/16
 */
class DeskDetailActivity : BaseActivity<DeskDetailViewMolder>() {

    private var itemView: RecyclerArrayAdapter.ItemView? = null

    companion object {
        fun start(cc: CC) {
            CCUtil.navigateTo(cc, DeskDetailActivity::class.java)
        }
    }

    override fun initLayout(): Int {
        return R.layout.desk_detail
    }

    override fun initViewAndData() {
        var vertical = ParseDataUtils.parseGSON(CCUtil.getNavigateParam(this, ComponentDetail.DETAIL_ACTIVITY_VERTICAL_DATA, ""), VerticalInfo.Vertical::class.java)
        LogUtils.e(vertical?.toString())
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