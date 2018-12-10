package com.androiddesk.desk.category.component.processor

import androiddesk.com.desk.base.component.processor.ComponentCategory
import androiddesk.com.desk.base.component.processor.IActionProcessor
import com.androiddesk.desk.category.component.v.CategoryFragment
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class CategoryFragmentProcessor : IActionProcessor {

    override fun getActionName(): String {
        return ComponentCategory.CATEGORY_FRAGMENT_ACTION
    }

    override fun onActionCall(cc: CC): Boolean {
        CC.sendCCResult(cc.callId, CCResult.success(ComponentCategory.CATEGORY_FRAGMENT_DATA, CategoryFragment.newInstance()))
        return false
    }
}