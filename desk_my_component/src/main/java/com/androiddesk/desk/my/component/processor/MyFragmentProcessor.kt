package com.androiddesk.desk.category.component.processor

import androiddesk.com.desk.base.component.processor.ComponentMy
import androiddesk.com.desk.base.component.processor.IActionProcessor
import com.androiddesk.desk.newest.component.v.MyFragment
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class MyFragmentProcessor : IActionProcessor {

    override fun getActionName(): String {
        return ComponentMy.MY_FRAGMENT_ACTION
    }

    override fun onActionCall(cc: CC): Boolean {
        CC.sendCCResult(cc.callId, CCResult.success(ComponentMy.MY_FRAGMENT_DATA, MyFragment.newInstance()))
        return false
    }
}