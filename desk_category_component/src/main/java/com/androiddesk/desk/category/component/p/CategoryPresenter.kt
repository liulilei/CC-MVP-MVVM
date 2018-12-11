package com.androiddesk.desk.category.component.p

import android.content.Context
import androiddesk.com.desk.base.component.base.RxPresenter
import com.androiddesk.desk.category.component.p.contract.CategoryContract
import javax.inject.Inject

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class CategoryPresenter @Inject
constructor(var context: Context?) : RxPresenter<CategoryContract.View>(), CategoryContract.Presenter {

}