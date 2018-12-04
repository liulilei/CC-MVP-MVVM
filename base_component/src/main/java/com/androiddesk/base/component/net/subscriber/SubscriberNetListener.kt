package io.newdex.exchange.net.subscriber

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/7
 */
interface SubscriberNetListener<in T> : SubscriberListener<T> {
    fun onNotNetError()
}