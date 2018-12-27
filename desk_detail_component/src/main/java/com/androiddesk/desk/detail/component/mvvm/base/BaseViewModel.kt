package com.androiddesk.desk.detail.component.mvvm.base

import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.NonNull
import androiddesk.com.desk.base.component.app.BaseApplication
import com.androiddesk.desk.detail.component.mvvm.util.GenericSuperUtils

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/27
 */
class BaseViewModel<T : BaseRepository>(@NonNull application: BaseApplication) : AndroidViewModel(application) {

    var mRepository: T? = null

    init {
        mRepository = GenericSuperUtils.getNewInstance(this, 0)
    }

    override fun onCleared() {
        super.onCleared()
        mRepository?.unDisposable()
    }
}