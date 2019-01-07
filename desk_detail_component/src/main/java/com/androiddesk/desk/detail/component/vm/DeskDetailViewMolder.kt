package com.androiddesk.desk.detail.component.vm

import android.app.Application
import android.content.Context
import com.androiddesk.desk.detail.component.mvvm.base.BaseViewModel
import com.androiddesk.desk.detail.component.vm.repository.DeskDetailRepository

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/29
 */
class DeskDetailViewMolder(application: Application) : BaseViewModel<DeskDetailRepository>(application) {

    fun getVerticalList(context: Context?, id: String, pageNo: Int, hasDialog: Boolean) {
        mRepository?.getVerticalList(context, id, pageNo, hasDialog)
    }


}