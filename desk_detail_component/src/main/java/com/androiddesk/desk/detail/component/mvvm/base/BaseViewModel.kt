package com.androiddesk.desk.detail.component.mvvm.base

import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.NonNull
import androiddesk.com.desk.base.component.app.BaseApplication
import javax.inject.Inject

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/27
 */
class BaseViewModel<T : BaseRepository>(@NonNull application: BaseApplication) : AndroidViewModel(application) {

    @Inject
    @JvmField
    var mRepository: T? = null

    override fun onCleared() {
        super.onCleared()
        mRepository?.unDisposable()
    }
}