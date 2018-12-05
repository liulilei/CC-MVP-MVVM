package androiddesk.com.desk.base.component.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by lll on 2017/2/15.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */

open class RxPresenter<T : BaseView> : BasePresenter<T> {

    protected var mView: T? = null

    private var compositeDisposable: CompositeDisposable? = null

    protected fun unSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
        }
    }

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable!!.add(disposable)
    }

    override fun attachView(view: T) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
        unSubscribe()
    }
}
