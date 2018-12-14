package com.androiddesk.desk.newest.component.p

import android.content.Context
import androiddesk.com.desk.base.component.base.RxPresenter
import com.androiddesk.base.component.constants.Constants
import com.androiddesk.base.component.net.subscriber.ResultDialogSubscriber
import com.androiddesk.base.component.net.subscriber.SubscriberListener
import com.androiddesk.base.component.utils.RxUtils
import com.androiddesk.desk.newest.component.R
import com.androiddesk.desk.newest.component.m.VerticalInfo
import com.androiddesk.desk.newest.component.net.ApiManager
import com.androiddesk.desk.newest.component.net.ParamsUtils
import com.androiddesk.desk.newest.component.p.contract.NewestContract
import javax.inject.Inject

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class NewestPresenter @Inject
constructor(var context: Context?) : RxPresenter<NewestContract.View>(), NewestContract.Presenter {

    override fun getVerticalList(id: String, pageNo: Int, hasDialog: Boolean) {
        var order: String
        var format = if (id.isNotEmpty()) {
            order = "hot"
            "/category/$id"
        } else {
            order = "new"
            ""
        }
        ApiManager.getNewApi()?.getVerticalNew(String.format(context?.getString(R.string.newest_url)!!, format), ParamsUtils.getVerticalList(pageNo * Constants.DEFAULT_PAGE_SIZE, Constants.DEFAULT_PAGE_SIZE, order))!!
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(ResultDialogSubscriber<VerticalInfo>(context!!, hasDialog, object : SubscriberListener<VerticalInfo> {
                    override fun onNext(t: VerticalInfo) {
                        mView?.showList(t.res?.vertical, pageNo)
                    }

                    override fun onError(error: String) {
                        mView?.showError(pageNo)
                    }

                }))
    }

}