package com.androiddesk.desk.category.component.v

import androiddesk.com.desk.base.component.app.BaseApplication
import androiddesk.com.desk.base.component.base.BaseFragment
import androiddesk.com.desk.base.component.base.RxPresenter
import com.androiddesk.desk.category.component.di.component.DaggerFragmentComponent
import com.androiddesk.desk.category.component.di.component.FragmentComponent

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
abstract class BaseMvpFragment<T : RxPresenter<*>> : BaseFragment<T>() {

    protected fun getFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
    }

}