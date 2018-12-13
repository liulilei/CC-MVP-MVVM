package com.androiddesk.desk.category.component.p

import android.content.Context
import androiddesk.com.desk.base.component.base.RxPresenter
import com.androiddesk.base.component.net.subscriber.ResultDialogSubscriber
import com.androiddesk.base.component.net.subscriber.SubscriberListener
import com.androiddesk.base.component.utils.RxUtils
import com.androiddesk.desk.category.component.m.CategoryInfo
import com.androiddesk.desk.category.component.net.ApiManager
import com.androiddesk.desk.category.component.p.contract.CategoryContract
import javax.inject.Inject

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class CategoryPresenter @Inject
constructor(var context: Context?) : RxPresenter<CategoryContract.View>(), CategoryContract.Presenter {
    override fun getCategoryList() {
        ApiManager.getCategoryApi()?.getCategory()!!
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(ResultDialogSubscriber<CategoryInfo>(context!!, object : SubscriberListener<CategoryInfo> {
                    override fun onNext(t: CategoryInfo) {
                        mView?.showList(t.res?.category)
                    }

                    override fun onError(error: String) {
                    }

                }))
    }

}