package androiddesk.com.desk.base.component.base

/**
 * Created by lll on 2017/2/15.
 * Presenter基类
 */

interface BasePresenter<T : BaseView> {

    fun attachView(view: T)

    fun detachView()
}
