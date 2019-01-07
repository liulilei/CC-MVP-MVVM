package com.androiddesk.desk.detail.component.vm.repository

import android.content.Context
import com.androiddesk.base.component.constants.Constants
import com.androiddesk.base.component.net.subscriber.ResultDialogSubscriber
import com.androiddesk.base.component.net.subscriber.SubscriberListener
import com.androiddesk.base.component.utils.RxUtils
import com.androiddesk.desk.detail.component.R
import com.androiddesk.desk.detail.component.m.VerticalInfo
import com.androiddesk.desk.detail.component.mvvm.base.BaseRepository
import com.androiddesk.desk.detail.component.net.ApiManager
import com.androiddesk.desk.detail.component.net.ParamsUtils

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/29
 */
class DeskDetailRepository : BaseRepository() {

    fun getVerticalList(context: Context?, id: String, pageNo: Int, hasDialog: Boolean) {
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

                    }

                    override fun onError(error: String) {

                    }

                }))
    }
}