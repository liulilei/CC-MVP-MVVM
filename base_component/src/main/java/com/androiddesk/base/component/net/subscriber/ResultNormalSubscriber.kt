package io.newdex.exchange.net.subscriber

import android.content.Context
import com.androiddesk.base.component.dialog.ProgressCancelListener
import com.androiddesk.base.component.dialog.ProgressDialogHandler
import com.androiddesk.base.component.utils.NetUtils
import io.reactivex.subscribers.ResourceSubscriber
import java.util.concurrent.TimeoutException

/**
 * Created by lll on 2016/6/2.
 */
class ResultNormalSubscriber<T>(private var context: Context?, hasDialog: Boolean, private val subscriberListener: SubscriberListener<T>?) : ResourceSubscriber<T>(), ProgressCancelListener {

    private var mProgressDialogHandler: ProgressDialogHandler? = null

    constructor(subscriberListener: SubscriberListener<T>) : this(null, false, subscriberListener)

    init {
        if (hasDialog) mProgressDialogHandler = ProgressDialogHandler(context!!, this, false)
    }

    private fun showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler!!.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget()
        }
    }

    private fun dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler!!.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget()
            mProgressDialogHandler = null
        }
    }

    override fun onCancelProgress() {
        if (!this.isDisposed) {
            this.dispose()
        }
    }

    public override fun onStart() {
        super.onStart()
        showProgressDialog()
    }

    override fun onComplete() {
        dismissProgressDialog()
        if (!this.isDisposed) {
            this.dispose()
        }
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        dismissProgressDialog()
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
//                ToastUtils.show("请检查网络")
            }
            return
        }
        if (e is TimeoutException) {
//            ToastUtils.show(R.string.R_string_time_out_server)
        } else {
//            ToastUtils.show(R.string.R_string_bad_server)
        }
    }

    override fun onNext(t: T) {
        if (subscriberListener != null) {
            try {
                subscriberListener.onNext(t)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
