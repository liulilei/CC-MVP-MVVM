package com.androiddesk.desk.detail.component.mvvm.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/27
 */
abstract class BaseRepository {

    private var mCompositeDisposable: CompositeDisposable? = null

    protected fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable)
    }

    fun unDisposable() {
        if (mCompositeDisposable != null && mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable!!.clear()
        }
    }
}