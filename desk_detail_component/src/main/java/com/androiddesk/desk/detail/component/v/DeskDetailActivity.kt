package com.androiddesk.desk.detail.component.v

import com.androiddesk.desk.detail.component.R
import com.androiddesk.desk.detail.component.mvvm.base.BaseActivity
import com.androiddesk.desk.detail.component.vm.DeskDetailViewMolder
import kotlinx.android.synthetic.main.desk_detail.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/16
 */
class DeskDetailActivity : BaseActivity<DeskDetailViewMolder>() {

    override fun initLayout(): Int {
        return R.layout.desk_detail
    }

    override fun initViewAndData() {
        tv.setOnClickListener {
            //            startActivity(Intent(this, AutoSizeDemo::class.java))
            mViewModel?.getVerticalList("", 1)
        }
    }
}