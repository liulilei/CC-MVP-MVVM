package com.androiddesk.desk.detail.component

import androiddesk.com.desk.base.component.processor.ComponentDetail
import androiddesk.com.desk.base.component.processor.IActionProcessor
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent
import com.billy.cc.core.component.IMainThread
import java.util.concurrent.atomic.AtomicBoolean

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/17
 */
class ComponentDetail : IComponent, IMainThread {

    private val initialized = AtomicBoolean(false)
    private val map: HashMap<String, IActionProcessor> = HashMap(4)


    private fun initProcessors() {}

    private fun add(processor: IActionProcessor) {
        map[processor.getActionName()] = processor
    }

    override fun onCall(cc: CC): Boolean {
        if (initialized.compareAndSet(false, true)) {
            synchronized(map) {
                initProcessors()
            }
        }
        val actionName = cc.actionName
        val processor = map[actionName]
        if (processor != null) {
            return processor.onActionCall(cc)
        }
        CC.sendCCResult(cc.callId, CCResult.error("has not support for action:" + cc.actionName))
        return false
    }

    override fun getName(): String {
        return ComponentDetail.COMPONENT_NAME
    }

    override fun shouldActionRunOnMainThread(actionName: String?, cc: CC?): Boolean? {
        return null
    }
}