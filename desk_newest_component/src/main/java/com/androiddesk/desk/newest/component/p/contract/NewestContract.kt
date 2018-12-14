package com.androiddesk.desk.newest.component.p.contract

import androiddesk.com.desk.base.component.base.BasePresenter
import androiddesk.com.desk.base.component.base.BaseView
import com.androiddesk.desk.newest.component.m.VerticalInfo

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
interface NewestContract {

    interface View : BaseView {
        fun showList(vertical: List<VerticalInfo.Vertical>?, pageNo: Int)

        fun showError(pageNo: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun getVerticalList(id: String, pageNo: Int, hasDialog: Boolean)
    }
}