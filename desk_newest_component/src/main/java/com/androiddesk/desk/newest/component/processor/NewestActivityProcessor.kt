package com.androiddesk.desk.newest.component.processor

import androiddesk.com.desk.base.component.processor.ComponentNewest
import androiddesk.com.desk.base.component.processor.IActionProcessor
import com.androiddesk.desk.newest.component.v.NewestActivity
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/14
 */
class NewestActivityProcessor : IActionProcessor {

    override fun getActionName(): String {
        return ComponentNewest.NEWEST_ACTIVITY_ACTION
    }

    override fun onActionCall(cc: CC): Boolean {
        NewestActivity.start(cc)
        CC.sendCCResult(cc.callId, CCResult.success())
        return false
    }
}