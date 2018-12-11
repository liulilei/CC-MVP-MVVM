package com.androiddesk.desk.category.component.p.contract

import androiddesk.com.desk.base.component.base.BasePresenter
import androiddesk.com.desk.base.component.base.BaseView

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
interface CategoryContract {

    interface View : BaseView {
    }

    interface Presenter : BasePresenter<View> {
    }
}