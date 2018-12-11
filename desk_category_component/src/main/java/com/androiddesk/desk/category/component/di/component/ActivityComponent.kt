package com.androiddesk.desk.category.component.di.component

import android.app.Activity
import androiddesk.com.desk.base.component.component.AppComponent
import androiddesk.com.desk.base.component.di.ActivityScope
import androiddesk.com.desk.base.component.module.ActivityModule
import dagger.Component

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun getActivity(): Activity

}