package com.androiddesk.desk.category.component.p.contract

import androiddesk.com.desk.base.component.base.BasePresenter
import androiddesk.com.desk.base.component.base.BaseView
import com.androiddesk.desk.category.component.m.CategoryInfo

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
interface CategoryContract {

    interface View : BaseView {
        fun showList(vertical: List<CategoryInfo.Category>?)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategoryList()
    }
}