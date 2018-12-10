package com.androiddesk.desk.category.component.processor

import androiddesk.com.desk.base.component.processor.ComponentNewest
import androiddesk.com.desk.base.component.processor.IActionProcessor
import com.androiddesk.desk.newest.component.v.NewestFragment
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class NewestFragmentProcessor : IActionProcessor {

    override fun getActionName(): String {
        return ComponentNewest.NEWEST_FRAGMENT_ACTION
    }

    override fun onActionCall(cc: CC): Boolean {
        CC.sendCCResult(cc.callId, CCResult.success(ComponentNewest.NEWEST_FRAGMENT_DATA, NewestFragment.newInstance()))
        return false
    }
}