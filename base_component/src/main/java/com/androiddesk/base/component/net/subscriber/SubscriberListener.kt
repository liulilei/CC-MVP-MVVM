package com.androiddesk.base.component.net.subscriber

/**
 * Created by lll on 2016/6/2.
 */
interface SubscriberListener<in T> {
    fun onNext(t: T)

    fun onError(error: String)
}
