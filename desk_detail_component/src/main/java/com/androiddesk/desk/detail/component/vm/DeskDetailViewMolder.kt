package com.androiddesk.desk.detail.component.vm

import android.app.Application
import androiddesk.com.desk.base.component.app.BaseApplication
import com.androiddesk.base.component.constants.Constants
import com.androiddesk.base.component.net.subscriber.ResultSubscriber
import com.androiddesk.base.component.net.subscriber.SubscriberListener
import com.androiddesk.base.component.utils.RxUtils
import com.androiddesk.desk.detail.component.R
import com.androiddesk.desk.detail.component.m.VerticalInfo
import com.androiddesk.desk.detail.component.mvvm.base.BaseViewModel
import com.androiddesk.desk.detail.component.net.ApiManager
import com.androiddesk.desk.detail.component.net.ParamsUtils
import com.androiddesk.desk.detail.component.vm.repository.DeskDetailRepository

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/29
 */
class DeskDetailViewMolder(application: Application) : BaseViewModel<DeskDetailRepository>(application) {

    fun getVerticalList(id: String, pageNo: Int) {
//        mRepository?.getVerticalList(id, pageNo, hasDialog)
        var order: String
        var format = if (id.isNotEmpty()) {
            order = "hot"
            "/category/$id"
        } else {
            order = "new"
            ""
        }
        ApiManager.getNewApi()?.getVerticalNew(String.format(BaseApplication.getInstance()?.getString(R.string.newest_url)!!, format), ParamsUtils.getVerticalList(pageNo * Constants.DEFAULT_PAGE_SIZE, Constants.DEFAULT_PAGE_SIZE, order))!!
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(ResultSubscriber<VerticalInfo>(BaseApplication.getInstance()!!, object : SubscriberListener<VerticalInfo> {
                    override fun onNext(t: VerticalInfo) {

                    }

                    override fun onError(error: String) {

                    }

                }))
    }


}