package com.androiddesk.desk.category.component.v

import androiddesk.com.desk.base.component.app.BaseApplication
import androiddesk.com.desk.base.component.base.BaseActivity
import androiddesk.com.desk.base.component.base.RxPresenter
import com.androiddesk.desk.category.component.di.component.ActivityComponent
import com.androiddesk.desk.category.component.di.component.DaggerActivityComponent

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
abstract class BaseMvpActivity<T : RxPresenter<*>> : BaseActivity<T>() {

    protected fun getActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build()
    }

}