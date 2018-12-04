package io.newdex.exchange.net.subscriber

import android.content.Context
import com.androiddesk.base.component.R
import com.androiddesk.base.component.mode.BaseMode
import com.androiddesk.base.component.utils.NetUtils
import com.androiddesk.base.component.utils.ToastUtils
import io.reactivex.subscribers.ResourceSubscriber
import java.util.concurrent.TimeoutException

/**
 * Created by lll on 2016/6/2.
 */
class ResultSubscriber<T>(private var context: Context, private val subscriberListener: SubscriberListener<T>?) : ResourceSubscriber<T>() {

    public override fun onStart() {
        super.onStart()
    }

    override fun onComplete() {
        if (!this.isDisposed) {
            this.dispose()
        }
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        if (!this.isDisposed) {
            this.dispose()
        }
        if (subscriberListener != null) {
            try {
                subscriberListener.onError(e.message!!)
            } catch (e1: Exception) {
                e1.printStackTrace()
            }

        }
        if (!NetUtils.isNetworkConnected(context)) {
            if (subscriberListener is SubscriberNetListener) {
                subscriberListener.onNotNetError()
            } else {
                ToastUtils.show(context, R.string.please_check_net)
            }
            return
        }
        if (e is TimeoutException) {
            ToastUtils.show(context, R.string.R_string_time_out_server)
        } else {
            ToastUtils.show(context, R.string.R_string_bad_server)
        }
    }

    override fun onNext(t: T) {
        val baseMode = t as BaseMode<*>
        if (NetUtils.successResult(baseMode.code)) {
            if (subscriberListener != null) {
                try {
                    subscriberListener.onNext(t)
                } catch (e: Exception) {
                    e.printStackTrace()
                    subscriberListener.onError(e.message!!)
                }

            }
        } else {
            ToastUtils.show(context, baseMode.msg!!)
            if (subscriberListener != null) {
                try {
                    subscriberListener.onError(baseMode.msg!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}
