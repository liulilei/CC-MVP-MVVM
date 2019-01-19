package com.androiddesk.desk.detail.component.processor

import androiddesk.com.desk.base.component.processor.ComponentDetail
import androiddesk.com.desk.base.component.processor.IActionProcessor
import com.androiddesk.desk.detail.component.v.DeskDetailActivity
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/17
 */
class DeskDetailActivityProcessor : IActionProcessor {
    override fun getActionName(): String {
        return ComponentDetail.DETAIL_ACTIVITY_ACTION
    }

    override fun onActionCall(cc: CC): Boolean {
        DeskDetailActivity.start(cc)
        CC.sendCCResult(cc.callId, CCResult.success())
        return false
    }
}