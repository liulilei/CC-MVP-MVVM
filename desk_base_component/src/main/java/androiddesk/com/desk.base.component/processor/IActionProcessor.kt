package androiddesk.com.desk.base.component.processor

import com.billy.cc.core.component.CC

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/7
 */
interface IActionProcessor {
    fun getActionName(): String

    /**
     * action的处理类
     * @param cc cc
     * @return 是否异步调用 [CC.sendCCResult] . true：异步， false：同步调用
     */
    fun onActionCall(cc: CC): Boolean
}