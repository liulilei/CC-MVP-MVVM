package com.androiddesk.desk.newest.component.v

import androiddesk.com.desk.base.component.base.SimpleActivity
import androiddesk.com.desk.base.component.processor.ComponentNewest
import com.androiddesk.desk.newest.component.R
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCUtil

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/14
 */
class NewestActivity : SimpleActivity() {

    companion object {
        fun start(cc: CC) {
            CCUtil.navigateTo(cc, NewestActivity::class.java)
        }
    }

    override fun initLayout(): Int {
        return R.layout.activity_newest
    }

    override fun loadRootFragment() {
        loadRootFragment(R.id.fl_container, NewestFragment.newInstance(
                CCUtil.getNavigateParam(this, ComponentNewest.NEWEST_ACTIVITY_TITLE, ""),
                CCUtil.getNavigateParam(this, ComponentNewest.NEWEST_ACTIVITY_ID, "")
        ))
    }
}