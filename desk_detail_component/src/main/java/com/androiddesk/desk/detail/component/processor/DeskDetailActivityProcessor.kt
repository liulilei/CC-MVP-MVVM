package com.androiddesk.desk.detail.component.processor

import androiddesk.com.desk.base.component.processor.IActionProcessor
import com.billy.cc.core.component.CC

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/17
 */
class DeskDetailActivityProcessor : IActionProcessor {
    override fun getActionName(): String {
        return ""
    }

    override fun onActionCall(cc: CC): Boolean {
        return false
    }
}