package com.androiddesk.base.component.net.subscriber

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/7
 */
interface SubscriberNetListener<in T> : SubscriberListener<T> {
    fun onNotNetError()
}