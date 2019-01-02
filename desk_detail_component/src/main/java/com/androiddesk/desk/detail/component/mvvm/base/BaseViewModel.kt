package com.androiddesk.desk.detail.component.mvvm.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.NonNull
import com.androiddesk.desk.detail.component.mvvm.util.GenericSuperUtils

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/27
 */
open class BaseViewModel<T : BaseRepository>(@NonNull application: Application) : AndroidViewModel(application) {

    var mRepository: T? = null

    init {
        mRepository = GenericSuperUtils.getNewInstance(this, 0)
    }

    override fun onCleared() {
        super.onCleared()
        mRepository?.unDisposable()
    }
}